package ltd.chuchen.service;

import ltd.chuchen.entity.Comment;
import ltd.chuchen.model.dto.CommentInfo;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论服务层接口
 */
public interface CommentService {

    int saveComment(CommentInfo commentInfo);

    int deleteCommentById(Long commentId);

    Comment getCommentById(Long id);

    int updateComment(Comment comment);

    List<Comment> getListCommentView();

}
