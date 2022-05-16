package ltd.chuchen.service.impl;

import ltd.chuchen.entity.Category;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.entity.VisitRecord;
import ltd.chuchen.mapper.*;
import ltd.chuchen.model.vo.CategoryBlogCount;
import ltd.chuchen.model.vo.TagBlogCount;
import ltd.chuchen.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 后台仪表数据服务层实现类
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private VisitLogMapper visitLogMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private VisitRecordMapper visitRecordMapper;

    //查询最近30天的记录
    private static final int visitRecordLimitNum = 30;

    @Override
    public int countVisitLogByToday() {
        return visitLogMapper.countVisitLogByToday();
    }

    @Override
    public int getBlogCount() {
        return blogMapper.selectCount(null);
    }

    @Override
    public int getCommentCount() {
        return commentMapper.selectCount(null);
    }

    /**
     * 分类博客情况
     */
    @Override
    public Map<String, List> getCategoryBlogCountMap() {
        //查询分类id对应的博客数量
        List<CategoryBlogCount> categoryBlogCountList = blogMapper.getCategoryBlogCountList();
        //查询所有分类的id和名称
        List<Category> categoryList = categoryMapper.selectAll();
        //所有分类名称的List
        List<String> legend = new ArrayList<>();
        for (Category category : categoryList) {
            legend.add(category.getCategoryName());
        }
        //分类对应的博客数量List
        List<CategoryBlogCount> series = new ArrayList<>();
        if (categoryBlogCountList.size() == categoryList.size()) {
            Map<Long, String> m = new HashMap<>();
            for (Category c : categoryList) {
                m.put(c.getId(), c.getCategoryName());
            }
            for (CategoryBlogCount c : categoryBlogCountList) {
                c.setName(m.get(c.getId()));
                series.add(c);
            }
        } else {
            Map<Long, Integer> m = new HashMap<>();
            for (CategoryBlogCount c : categoryBlogCountList) {
                m.put(c.getId(), c.getValue());
            }
            for (Category c : categoryList) {
                CategoryBlogCount categoryBlogCount = new CategoryBlogCount();
                categoryBlogCount.setId(c.getId());
                categoryBlogCount.setName(c.getCategoryName());
                Integer count = m.get(c.getId());
                if (count == null) {
                    categoryBlogCount.setValue(0);
                } else {
                    categoryBlogCount.setValue(count);
                }
                series.add(categoryBlogCount);
            }
        }
        Map<String, List> map = new HashMap<>();
        map.put("legend", legend);
        map.put("series", series);
        return map;
    }

    @Override
    public Map<String, List> getTagBlogCountMap() {
        //查询标签id对应的博客数量
        List<TagBlogCount> tagBlogCountList = tagMapper.getTagBlogCount();
        //查询所有标签的id和名称
        List<Tag> tagList = tagMapper.selectAll();
        //所有标签名称的List
        List<String> legend = new ArrayList<>();
        for (Tag tag : tagList) {
            legend.add(tag.getTagName());
        }
        //标签对应的博客数量List
        List<TagBlogCount> series = new ArrayList<>();
        if (tagBlogCountList.size() == tagList.size()) {
            Map<Long, String> m = new HashMap<>();
            for (Tag t : tagList) {
                m.put(t.getId(), t.getTagName());
            }
            for (TagBlogCount t : tagBlogCountList) {
                t.setName(m.get(t.getId()));
                series.add(t);
            }
        } else {
            Map<Long, Integer> m = new HashMap<>();
            for (TagBlogCount t : tagBlogCountList) {
                m.put(t.getId(), t.getValue());
            }
            for (Tag t : tagList) {
                TagBlogCount tagBlogCount = new TagBlogCount();
                tagBlogCount.setName(t.getTagName());
                tagBlogCount.setId(t.getId());
                Integer count = m.get(t.getId());
                if (count == null) {
                    tagBlogCount.setValue(0);
                } else {
                    tagBlogCount.setValue(count);
                }
                series.add(tagBlogCount);
            }
        }
        Map<String, List> map = new HashMap<>();
        map.put("legend", legend);
        map.put("series", series);
        return map;
    }

    @Override
    public Map<String, List> getVisitRecordMap() {
        List<VisitRecord> visitRecordList = visitRecordMapper.getVisitRecordListByLimit(visitRecordLimitNum);
        List<String> date = new ArrayList<>(visitRecordList.size());
        List<Integer> pv = new ArrayList<>(visitRecordList.size());
        List<Integer> uv = new ArrayList<>(visitRecordList.size());

        for (int i = visitRecordList.size() - 1; i >= 0; i--) {
            VisitRecord visitRecord = visitRecordList.get(i);
            date.add(visitRecord.getDate());
            pv.add(visitRecord.getPv());
            uv.add(visitRecord.getUv());
        }

        Map<String, List> map = new HashMap<>();
        map.put("date", date);
        map.put("pv", pv);
        map.put("uv", uv);
        return map;
    }
}
