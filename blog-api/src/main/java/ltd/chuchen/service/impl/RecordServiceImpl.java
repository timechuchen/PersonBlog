package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Record;
import ltd.chuchen.mapper.RecordMapper;
import ltd.chuchen.model.dto.RecordInfo;
import ltd.chuchen.service.RecordService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    private void init() {
        updateLikesToRedis();
    }

    @Override
    public Boolean saveMoment(RecordInfo recordInfo) {
        Record record = new Record();
        record.setTitle(recordInfo.getTitle());
        record.setContent(recordInfo.getContent());
        record.setPublished(recordInfo.getPublished());
        int insert = recordMapper.insert(record);
        if(insert == 1) {
            updateRecordOfRedis();
            return true;
        }
        return false;
    }

    /**
     * 因为做过用了分页插件，所以没有做 redis 缓存
     */
    @Override
    public IPage<Record> selectPage(Page<Record> page) {
        return recordMapper.selectPage(page,null);
    }

    @Override
    public List<Record> selectAll() {
        return recordMapper.selectAll();
    }

    @Override
    public Boolean updateMomentPublishedById(Long id, Boolean published) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Record record = recordMapper.selectOne(queryWrapper);
        record.setPublished(published);
        int i = recordMapper.updateById(record);
        if(i == 1) {
            updateRecordOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRecordById(Long id) {
        int i = recordMapper.deleteById(id);
        if(i == 1) {
            updateRecordOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public Record getRecordById(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public boolean updateRecord(Record record) {
        int i = recordMapper.updateById(record);
        if(i == 1) {
            updateRecordOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public List<Record> selectAllByDesc() {
        String redisKey = RedisKeyConstant.RECORD_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateRecordOfRedis();
        }
        List<Record> records = JSON.parseArray(JSON.toJSONString(o),Record.class);

        if(records != null) {
            return records;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateRecordOfRedis();
    }

    /**
     * 从数据库同步动态点赞数到redis
     */
    public void updateLikesToRedis() {
        String redisKey = RedisKeyConstant.RECORD_LIKES;
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        try {
            queryWrapper.select("id", "likes");//指定查询某字段
            List<Record> records = recordMapper.selectList(queryWrapper);
            records.forEach((o)->{
                redisUtil.hset(redisKey, String.valueOf(o.getId()),o.getLikes());
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从redis同步动态点赞数到数据库
     * @return 是否同步成功
     */
    public boolean updateLikesToMySql() {
        String redisKey = RedisKeyConstant.RECORD_LIKES;
        try {
            Map<Object, Object> hmget = redisUtil.hmget(redisKey);
            System.out.println(hmget);
            hmget.forEach((k,v)->{
                Record record = recordMapper.selectById((Serializable) k);
                record.setLikes((Integer) v);
                recordMapper.updateById(record);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 增加 Redis 中的对应动态的点赞信息
     * @param commentId 动态Id
     */
    public void updateLikes(String commentId) {
        String redisKey = RedisKeyConstant.RECORD_LIKES;
        redisUtil.hincr(redisKey,commentId,1);
    }

    /**
     * 获取 redis 中的点赞信息
     * @return 返回点赞信息
     */
    public Map getLikesOfRecord() {
        String redisKey = RedisKeyConstant.RECORD_LIKES;
        return redisUtil.hmget(redisKey);
    }

    /**
     * 更新 redis 中动态信息
     */
    protected List<Record> updateRecordOfRedis() {
        String redisKey = RedisKeyConstant.RECORD_LIST;
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<Record> records = recordMapper.selectList(queryWrapper);
        redisUtil.set(redisKey,records);
        return records;
    }
}
