package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.entity.Comment;
import ltd.chuchen.enums.VisitBehavior;
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

    @VisitLogger(VisitBehavior.CHECK_PASSWORD)
    @GetMapping("/comments")
    @ResponseBody
    public Result getCommentListByQuery() {
        List<CommentShow> comments = commentService.getAllComments();
        return Result.ok("ok",comments);
    }

    @OperationLogger("删除评论")
    @DeleteMapping("/comment")
    @ResponseBody
    public Result deleteCommentById(@RequestParam("id") Long id) {
        if(commentService.deleteCommentById(id)) {
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
    @OperationLogger("修改评论是否公开")
    @PutMapping("/comment/published")
    @ResponseBody
    public Result updatePublished(@RequestParam Long id, @RequestParam Boolean isPublished) {
        if(commentService.updateCommentPublishedById(id, isPublished)) {
            return Result.ok("ok");
        }else {
            return Result.error("fail");
        }
    }

    @OperationLogger("修改评论")
    @PutMapping("/comment")
    @ResponseBody
    public Result updateComment(@RequestBody CommentUpdate comment) {
        if (StringUtils.isEmpty(comment.getNickname(), comment.getAvatar(), comment.getContent())) {
            return Result.error("参数有误");
        }
        if(commentService.updateComment(comment)) {
            return Result.ok("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

}
