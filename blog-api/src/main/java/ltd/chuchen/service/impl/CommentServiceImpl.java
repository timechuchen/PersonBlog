package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.entity.Comment;
import ltd.chuchen.entity.User;
import ltd.chuchen.mapper.BlogMapper;
import ltd.chuchen.mapper.CommentMapper;
import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.model.dto.CommentInfo;
import ltd.chuchen.model.dto.CommentShow;
import ltd.chuchen.model.dto.CommentUpdate;
import ltd.chuchen.model.dto.CommentView;
import ltd.chuchen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论服务层实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int saveComment(CommentInfo commentInfo) {
        User user = userMapper.selectById(commentInfo.getAuthorId());
        Comment comment = new Comment();
        comment
                .setAvatar(user.getAvatar())
                .setContent(commentInfo.getContext())
                .setNickname(user.getUsername())
                .setEmail(user.getEmail())
                .setPage(commentInfo.getPage())
                .setParentCommentId(comment.getParentCommentId())
                .setBlogId(commentInfo.getBlogId())
                .setIsPublished(true);
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteCommentById(Long commentId) {
        return commentMapper.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public int updateComment(CommentUpdate comment) {
        Comment c = commentMapper.selectById(comment.getId());
        c.setNickname(comment.getNickname()).setAvatar(comment.getAvatar()).setEmail(comment.getEmail()).setContent(comment.getContent());
        return commentMapper.updateById(c);
    }

    @Override
    public List<Comment> getListCommentView() {
        return null;
    }

    @Override
    public List<CommentShow> getAllComments() {
        List<CommentShow> result = new LinkedList<>();
        List<Comment> comments = commentMapper.selectList(null);
        for(Comment c : comments) {
            String blogTitle = blogMapper.selectById(c.getBlogId()).getTitle();
            result.add(new CommentShow()
                    .setId(c.getId())
                    .setNickname(c.getNickname())
                    .setPage(c.getPage())
                    .setAvatar(c.getAvatar())
                    .setBlogTitle(blogTitle)
                    .setEmail(c.getEmail())
                    .setCreateTime(c.getCreateTime())
                    .setContent(c.getContent())
                    .setIsPublished(c.getIsPublished()));
        }
        return result;
    }

    @Override
    public int updateCommentPublishedById(Long id, Boolean published) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Comment comment = commentMapper.selectOne(queryWrapper);
        if(comment == null) {
            return 0;
        }
        comment.setIsPublished(published);
        return commentMapper.updateById(comment);
    }

    @Override
    public List<CommentView> getCommentByBlogId(Long blogId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("is_published",true);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentView> result = new LinkedList<>();
        for(Comment c : comments) {
            result.add(new CommentView()
                .setId(c.getId())
                .setAuthor(c.getNickname())
                .setTime(c.getCreateTime())
                .setImg(c.getAvatar())
                .setContent(c.getContent()));
        }
        return result;
    }
}
