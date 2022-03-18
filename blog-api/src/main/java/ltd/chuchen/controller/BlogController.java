package ltd.chuchen.controller;

import ltd.chuchen.model.dto.BlogViewListInfo;
import ltd.chuchen.model.dto.HotTagInfo;
import ltd.chuchen.model.vo.BlogInfos;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/18
 * @Description 前台控制层
 */
@Controller
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ResponseBody
    @GetMapping("/blogList")
    public Result loadHotTag() {
        List<BlogViewListInfo> blogViewList = blogService.getBlogViewList();
        if(blogViewList.size() != 0){
            return Result.ok("数据加载成功",blogViewList);
        }else {
            return Result.error("数据加载失败!");
        }
    }

    @ResponseBody
    @GetMapping("/blog")
    public Result getBlog(@RequestParam Long blogId) {
        BlogInfos blog = blogService.getBlogById(blogId);
        if(blog != null) {
            return Result.ok("ok",blog);
        }else {
            return Result.error("ok");
        }
    }
}
