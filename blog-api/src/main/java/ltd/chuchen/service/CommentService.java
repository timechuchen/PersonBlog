package ltd.chuchen.service;

import ltd.chuchen.entity.Comment;
import ltd.chuchen.model.dto.CommentInfo;
import ltd.chuchen.model.dto.CommentShow;
import ltd.chuchen.model.dto.CommentUpdate;
import ltd.chuchen.model.dto.CommentView;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论服务层接口
 */
public interface CommentService {

    Boolean saveComment(CommentInfo commentInfo);

    Boolean deleteCommentById(Long commentId);

    Comment getCommentById(Long id);

    Boolean updateComment(CommentUpdate comment);

    List<Comment> getListCommentView();

    List<CommentShow> getAllComments();

    Boolean updateCommentPublishedById(Long id, Boolean published);

    List<CommentView> getCommentByBlogId(Long blogId);

    void deleteCommentByBlogId(Long id);

    Integer getCommentCountByBlogId(Long id);
}
