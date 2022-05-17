package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论映射文件
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
