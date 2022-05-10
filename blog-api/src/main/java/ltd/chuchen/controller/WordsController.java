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
 * @Date 2022/5/10
 * @Description 留言板信息控制层
 */
@Controller
@RequestMapping("/api")
public class WordsController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/words")
    @ResponseBody
    public Result getWords() {
        List<CommentView> words = commentService.getAllWords();
        return Result.ok("获取数据成功",words);
    }

    @ResponseBody
    @PostMapping("/word")
    public Result saveComment(@RequestBody CommentInfo commentInfo) {
        if(commentService.addWord(commentInfo)) {
            return Result.ok("ok");
        }else {
            return Result.error("服务器错误");
        }
    }
}
