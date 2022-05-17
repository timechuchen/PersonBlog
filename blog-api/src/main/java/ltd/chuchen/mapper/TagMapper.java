package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.model.vo.TagBlogCount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/15
 * @Description Tag 的 Mapper 映射
 */
@Mapper
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select * from cblog.tag")
    List<Tag> selectAll();

    @Select("select tag_id from blog_tag where blog_id = #{blogId}")
    List<Long> selectIdByBlogId(Long blogId);

    @Select("select tag_id, count(tag_id) as blog_count from blog_tag group by tag_id")
    @Results(value = {
            @Result(id = true,column = "tag_id",property = "id"),
            @Result(column = "blog_count",property = "value")
    })
    List<TagBlogCount> getTagBlogCount();

    @Delete("delete from blog_tag where tag_id = #{id}")
    int deleteOfTagAndBlog(Long id);
}
