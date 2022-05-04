package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Record;
import ltd.chuchen.mapper.RecordMapper;
import ltd.chuchen.model.dto.BlogViewListInfo;
import ltd.chuchen.model.dto.RecordInfo;
import ltd.chuchen.service.RecordService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        updateRecordOfRedis();
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
