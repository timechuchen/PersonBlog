package ltd.chuchen.controller;

import ltd.chuchen.model.dto.BlogViewListInfo;
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
            return Result.error("error");
        }
    }

    @ResponseBody
    @GetMapping("/recommendBlogs")
    public Result getRecommendBlog() {
        List<BlogViewListInfo> blogViewList = blogService.getRecommendBlog();
        if(blogViewList.size() != 0){
            return Result.ok("数据加载成功",blogViewList);
        }else {
            return Result.error("数据加载失败!");
        }
    }

    @ResponseBody
    @GetMapping("/blogByTag")
    public Result getBlogByTag(@RequestParam String category) {
        List<BlogViewListInfo> blogViewList = blogService.getBlogByCategory(category);
        if(blogViewList == null || blogViewList.size() != 0){
            return Result.ok("数据加载成功",blogViewList);
        }else {
            return Result.error("数据加载失败!");
        }
    }

    @ResponseBody
    @GetMapping("/blogSearch")
    public Result getBlogBySearch(@RequestParam String search) {
        List<BlogViewListInfo> blogViewList = blogService.getBlogBysearch(search);
        if(blogViewList == null){
            return Result.ok("没有数据");
        } else if(blogViewList.size() != 0){
            return Result.ok("数据加载成功",blogViewList);
        }else {
            return Result.error("数据加载失败!");
        }
    }
}
