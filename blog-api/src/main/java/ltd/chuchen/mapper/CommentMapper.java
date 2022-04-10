package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Comment;
import ltd.chuchen.model.dto.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论映射文件
 */
@Mapper
@Component
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    int saveComment(CommentInfo commentInfo);

    int deleteCommentById(Long commentId);

    Comment getCommentById(Long id);

    int updateComment(Comment comment);

    List<Comment> getListCommentView();

}
