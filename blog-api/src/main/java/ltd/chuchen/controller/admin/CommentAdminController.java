package ltd.chuchen.controller.admin;

import ltd.chuchen.entity.Comment;
import ltd.chuchen.model.dto.CommentShow;
import ltd.chuchen.model.dto.CommentUpdate;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.CommentService;
import ltd.chuchen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/4/12
 * @Description 评论管理控制层
 */
@Controller
@RequestMapping("/api/admin")
public class CommentAdminController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    @ResponseBody
    public Result getCommentListByQuery() {
        List<CommentShow> comments = commentService.getAllComments();
        return Result.ok("ok",comments);
    }

    @DeleteMapping("/comment")
    @ResponseBody
    public Result deleteCommentById(@RequestParam("id") Long id) {
        int i = commentService.deleteCommentById(id);
        if(i == 1) {
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * 更新评论公开状态
     *
     * @param id        评论id
     * @param isPublished 是否公开
     */
    @PutMapping("/comment/published")
    @ResponseBody
    public Result updatePublished(@RequestParam Long id, @RequestParam Boolean isPublished) {
        int i = commentService.updateCommentPublishedById(id, isPublished);
        if(i == 1) {
            return Result.ok("ok");
        }else {
            return Result.error("fail");
        }
    }

    @PutMapping("/comment")
    @ResponseBody
    public Result updateComment(@RequestBody CommentUpdate comment) {
        if (StringUtils.isEmpty(comment.getNickname(), comment.getAvatar(), comment.getContent())) {
            return Result.error("参数有误");
        }
        int i = commentService.updateComment(comment);
        if(i == 1) {
            return Result.ok("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

}
