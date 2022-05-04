package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Record;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.mapper.TagMapper;
import ltd.chuchen.service.TagService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 初晨
 * @Date 2022/3/15
 * @Description 标签service层
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    private void init() {
        updateTagsOfRedis();
    }

    @Override
    public List<Tag> getTagList() {
        String redisKey = RedisKeyConstant.TAG_CLOUD_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateTagsOfRedis();
        }
        List<Tag> tags = JSON.parseArray(JSON.toJSONString(o),Tag.class);

        if(tags != null) {
            return tags;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateTagsOfRedis();
    }


    @Override
    public List<Tag> getTagListNotId() {
        return null;
    }

    @Override
    public List<Tag> getTagListByBlogId(Long blogId) {
        List<Tag> tags = new LinkedList<>();
        List<Long> ids = tagMapper.selectIdByBlogId(blogId);

        for(Long id : ids){
            Tag tag = tagMapper.selectById(id);
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public Integer saveTag(Tag tag) {
        Tag tagByName = this.getTagByName(tag.getTagName());
        if(tagByName != null) {
            return 1;
        }
        int insert = tagMapper.insert(tag);
        if(insert == 1) {
            updateTagsOfRedis();
            return 0;
        }
        else {
            return 2;
        }
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_name",name);
        return tagMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean deleteTagById(Long id) {
        int i = tagMapper.deleteById(id);
        if(i == 1) {
            updateTagsOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateTag(Tag tag) {
        int i = tagMapper.updateById(tag);
        if(i == 1) {
            updateTagsOfRedis();
            return true;
        }
        return false;
    }

    /**
     * 更新 redis 中的标签
     */
    protected List<Tag> updateTagsOfRedis() {
        String redisKey = RedisKeyConstant.TAG_CLOUD_LIST;
        List<Tag> tags = tagMapper.selectAll();
        redisUtil.set(redisKey,tags);
        return tags;
    }
}
