package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.mapper.TagMapper;
import ltd.chuchen.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Tag> getTagList() {
        return tagMapper.selectAll();
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
        if(insert == 1) return 0;
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
        return i == 1;
    }

    @Override
    public Boolean updateTag(Tag tag) {
        int i = tagMapper.updateById(tag);
        return i == 1;
    }
}
