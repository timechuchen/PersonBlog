package ltd.chuchen.controller;

import ltd.chuchen.model.dto.CommentInfo;
import ltd.chuchen.model.dto.CommentView;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/4/11
 * @Description 前端评论控制层
 */
@Controller
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     * @param commentInfo 评论信息
     * @return result
     */
    @ResponseBody
    @PostMapping("/comment")
    public Result saveComment(@RequestBody CommentInfo commentInfo) {
        int i = commentService.saveComment(commentInfo);
        if(i == 1) {
            return Result.ok("ok");
        }else {
            return Result.error("服务器错误");
        }
    }

    @ResponseBody
    @GetMapping("/blogComment")
    public Result getCommentByBlogId(@RequestParam Long blogId) {
        List<CommentView> comments = commentService.getCommentByBlogId(blogId);
        return Result.ok("ok",comments);
    }
}
