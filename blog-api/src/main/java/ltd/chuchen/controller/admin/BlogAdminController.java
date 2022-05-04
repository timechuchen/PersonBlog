package ltd.chuchen.controller.admin;

import ltd.chuchen.model.dto.BlogInfo;
import ltd.chuchen.model.dto.BlogVisibility;
import ltd.chuchen.model.vo.BlogInfos;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/3/16
 * @Description 博客管理控制层
 */
@Controller
@RequestMapping("/api/admin")
public class BlogAdminController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    @ResponseBody
    public Result getBlogList() {
        Map<String, Object> blogListInfo = blogService.getBlogListInfo();
        return Result.ok("成功",blogListInfo);
    }

    @PostMapping("/blog")
    @ResponseBody
    public Result saveBlog(@RequestBody BlogInfo blog) {
        Integer save = blogService.saveBlog(blog, "save");
        if(save == 0) {
            return Result.create(201,"参数有误");
        }else  if(save == 1) {
            return Result.ok("添加成功");
        }else  if(save == 2) {
            return Result.create(203,"分类不能为空");
        }else  if(save == 5 || save == 6) {
            return Result.error("服务器错误");
        }else  if(save == 7) {
            return Result.create(204,"标签映射失败");
        }else  if(save == 8) {
            return Result.error("服务器错误");
        }
        return Result.ok("保存成功",blog);
    }

    @PutMapping("/blog")
    @ResponseBody
    public Result updateBlog(@RequestBody BlogInfo blog) {
        Integer save = blogService.saveBlog(blog, "update");
        if(save == 0) {
            return Result.create(201,"参数有误");
        }else  if(save == 1) {
            return Result.ok("修改成功",blog);
        }else  if(save == 2) {
            return Result.create(203,"分类不能为空");
        }else  if(save == 5 || save == 6) {
            return Result.error("服务器错误");
        }else  if(save == 7) {
            return Result.create(204,"标签映射失败");
        }else  if(save == 8) {
            return Result.error("服务器错误");
        }
        return Result.ok("修改成功",blog);
    }

    @DeleteMapping("/blog")
    @ResponseBody
    public Result deleteBlog(@RequestParam Long id) {
        if(blogService.deleteBlogById(id)) {
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @GetMapping("/categoryAndTag")
    @ResponseBody
    public Result getCategoryAndTag() {
        Map<String, Object> categoryAndTag = blogService.getCategoryAndTag();
        return Result.ok("请求成功！",categoryAndTag);
    }

    @PutMapping("/blog/top")
    @ResponseBody
    public Result updateTop(@RequestParam Long id, @RequestParam Boolean top) {
        if(blogService.updateBlogTopById(id,top)) {
            return Result.ok("成功！");
        }else {
            return Result.error("失败");
        }
    }

    @PutMapping("/blog/recommend")
    @ResponseBody
    public Result updateRecommend(@RequestParam Long id, @RequestParam Boolean recommend) {
        if(blogService.updateBlogRecommendById(id,recommend)){
            return Result.ok("成功！");
        }else {
            return Result.error("失败");
        }
    }

    @PutMapping("/blog/{id}/visibility")
    @ResponseBody
    public Result updateVisibility(@PathVariable Long id, @RequestBody BlogVisibility blogVisibility) {
        if(blogService.updateBlogVisibilityById(id,blogVisibility)){
            return Result.ok("成功！");
        }else {
            return Result.error("失败");
        }
    }

    @GetMapping("/blog")
    @ResponseBody
    public Result getBlogById(@RequestParam Long id) {
        BlogInfos blog = blogService.getBlogById(id);
        if(blog != null) {
            return Result.ok("ok",blog);
        }else {
            return Result.error("服务器错误");
        }
    }
}
