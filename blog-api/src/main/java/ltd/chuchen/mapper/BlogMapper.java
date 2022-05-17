package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Blog;
import ltd.chuchen.model.dto.BlogView;
import ltd.chuchen.model.vo.CategoryBlogCount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/16
 * @Description 博客管理映射文件
 */
@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    @Insert("insert into blog_tag (blog_id, tag_id) values (#{blogId}, #{tagId})")
    Integer saveBlogTag(Long blogId,Long tagId);

    @Delete("delete from blog_tag where blog_id = #{id}")
    void deleteTag(Long id);

    @Update("update blog set recommend = #{recommend} where id = #{blogId}")
    int updateRecommendById(Long blogId, Boolean recommend);

    @Update("update blog set top = #{top} where id = #{blogId}")
    int updateTopById(Long blogId, Boolean top);

    @Update("update blog set views = #{views} where id = #{blogId}")
    int updateViewsById(Long blogId, Integer views);

    @Update("update blog set recommend = #{recommend},appreciation = #{appreciation},published = #{published},comment_enabled = #{commentEnabled},top = #{top},password = #{password} where id = #{blogId}")
    int updateVisibilityById(Long blogId, Boolean recommend, Boolean appreciation, Boolean published, Boolean commentEnabled, Boolean top, String password);

    @Select("select id,views from blog")
    List<BlogView> getBlogViewsList();

    @Select("select category_id, count(category_id) as blog_count from blog group by category_id")
    @Results(value = {
            @Result(id = true,column = "category_id",property = "id"),
            @Result(column = "blog_count",property = "value")
    })
    List<CategoryBlogCount> getCategoryBlogCountList();
}
