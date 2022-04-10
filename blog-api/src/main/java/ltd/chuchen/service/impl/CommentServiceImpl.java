package ltd.chuchen.service.impl;

import ltd.chuchen.entity.Comment;
import ltd.chuchen.model.dto.CommentInfo;
import ltd.chuchen.service.CommentService;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论服务层实现类
 */
public class CommentServiceImpl implements CommentService {
    @Override
    public int saveComment(CommentInfo commentInfo) {
        return 0;
    }

    @Override
    public int deleteCommentById(Long commentId) {
        return 0;
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public int updateComment(Comment comment) {
        return 0;
    }

    @Override
    public List<Comment> getListCommentView() {
        return null;
    }
}
