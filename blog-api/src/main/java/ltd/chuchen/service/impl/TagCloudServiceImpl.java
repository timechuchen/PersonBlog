package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Category;
import ltd.chuchen.entity.TagCloud;
import ltd.chuchen.mapper.TagCloudMapper;
import ltd.chuchen.model.vo.TagCloudView;
import ltd.chuchen.service.TagCloudService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/17
 * @Description 标签云服务层实现类
 */
@Service
public class TagCloudServiceImpl implements TagCloudService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TagCloudMapper tagCloudMapper;

    @Override
    public boolean addTagCloud(TagCloudView tagCloudView) {
        TagCloud tagCloud = new TagCloud();
        tagCloud.setId(tagCloudView.getId());
        tagCloud.setTitle(tagCloudView.getTitle());
        tagCloud.setUrl(tagCloudView.getUrl());
        tagCloud.setColor(tagCloudView.getColor());
        tagCloud.setSize(tagCloudView.getSize());
        int insert = tagCloudMapper.insert(tagCloud);
        if(insert == 1) {
            this.updateInfoRedis();
            return true;
        }
        return false;
    }

    @Override
    public List<TagCloudView> getAllTagCloud() {
        String redisKey = RedisKeyConstant.TAG_CLOUD;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateInfoRedis();
        }
        List<TagCloudView> tagCloudViews = JSON.parseArray(JSON.toJSONString(o),TagCloudView.class);

        if(tagCloudViews != null) {
            return tagCloudViews;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateInfoRedis();
    }

    @Override
    public boolean updateTagCloudById(TagCloudView tagCloudView) {
        TagCloud tagCloud = tagCloudMapper.selectById(tagCloudView.getId());
        tagCloud.setColor(tagCloudView.getColor());
        tagCloud.setTitle(tagCloudView.getTitle());
        tagCloud.setSize(tagCloudView.getSize());
        tagCloud.setUrl(tagCloudView.getUrl());
        int i = tagCloudMapper.updateById(tagCloud);
        if(i == 1) {
            updateInfoRedis();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTagCloudById(Long id) {
        int i = tagCloudMapper.deleteById(id);
        if(i == 1) {
            updateInfoRedis();
            return true;
        }
        return false;
    }

    /**
     * 同步数据库和redis中标签云信息
     */
    protected List<TagCloudView> updateInfoRedis() {
        String redisKey = RedisKeyConstant.TAG_CLOUD;
        List<TagCloud> tagClouds = tagCloudMapper.selectList(null);
        List<TagCloudView> tagCloudViews = new ArrayList<>();
        tagClouds.forEach(o-> tagCloudViews.add(new TagCloudView()
                .setId(o.getId())
                .setTitle(o.getTitle())
                .setSize(o.getSize())
                .setUrl(o.getUrl())
                .setColor(o.getColor())));
        redisUtil.set(redisKey,tagCloudViews);
        return tagCloudViews;
    }
}
