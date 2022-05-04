package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Comment;
import ltd.chuchen.entity.User;
import ltd.chuchen.mapper.BlogMapper;
import ltd.chuchen.mapper.CommentMapper;
import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.model.dto.*;
import ltd.chuchen.service.CommentService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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
    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    private void init() {
        updateAllCommentsOfRedis();
    }

    @Override
    public Boolean saveComment(CommentInfo commentInfo) {
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
        int insert = commentMapper.insert(comment);
        if(insert == 1) {
            updateAllCommentsOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCommentById(Long commentId) {
        int i = commentMapper.deleteById(commentId);
        if(i == 1) {
            updateAllCommentsOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public Boolean updateComment(CommentUpdate comment) {
        Comment c = commentMapper.selectById(comment.getId());
        c.setNickname(comment.getNickname()).setAvatar(comment.getAvatar()).setEmail(comment.getEmail()).setContent(comment.getContent());
        int i = commentMapper.updateById(c);
        if(i == 1) {
            updateAllCommentsOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getListCommentView() {
        return null;
    }

    @Override
    public List<CommentShow> getAllComments() {
        String redisKey = RedisKeyConstant.COMMENT_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateAllCommentsOfRedis();
        }
        List<CommentShow> commentShows = JSON.parseArray(JSON.toJSONString(o),CommentShow.class);
        if(commentShows != null) {
            return commentShows;
        }
        return updateAllCommentsOfRedis();
    }

    @Override
    public Boolean updateCommentPublishedById(Long id, Boolean published) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Comment comment = commentMapper.selectOne(queryWrapper);
        if(comment == null) {
            return false;
        }
        comment.setIsPublished(published);
        int i = commentMapper.updateById(comment);
        if(i == 1) {
            updateAllCommentsOfRedis();
            return true;
        }
        return false;
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

    /**
     * 通过博客id删除评论
     * @param id 博客id
     */
    @Override
    public void deleteCommentByBlogId(Long id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("blog_id",id);
        commentMapper.deleteByMap(map);
        updateAllCommentsOfRedis();
    }

    /**
     * 获取指定博客的评论数量
     * @param id 博客id
     * @return 博客的评论数
     */
    @Override
    public Integer getCommentCountByBlogId(Long id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",id);
        return commentMapper.selectCount(queryWrapper);
    }

    /**
     * 更新 redis 中评论信息
     */
    protected List<CommentShow> updateAllCommentsOfRedis() {
        String redisKey = RedisKeyConstant.COMMENT_LIST;
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
        redisUtil.set(redisKey,result);
        return result;
    }
}
