package ltd.chuchen.controller.admin;

import ltd.chuchen.entity.Category;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/15
 * @Description 分类管理控制器
 */

@Controller
@RequestMapping("/api/admin")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     * @param category 分类对象
     * @return Result
     */
    @PostMapping("/category")
    @ResponseBody
    public Result saveCategory(@RequestBody Category category) {
        Integer ret = categoryService.saveCategory(category);
        if(ret == 0) {
            return Result.ok("添加成功");
        }else if(ret == 1){
            return Result.create(202,"分类已存在");
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 后去所有分类信息
     * @return data为所有的分类信息
     */
    @GetMapping("/categories")
    @ResponseBody
    public Result getAllCategory() {
        List<Category> categoryList = categoryService.getCategoryList();
        if(categoryList != null) {
            return Result.ok("查找成功",categoryList);
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 通过id删除分类信息
     * @param id id
     * @return Result
     */
    @DeleteMapping("/category")
    @ResponseBody
    public Result deleteCategory(@RequestParam Long id) {
        if(categoryService.deleteCategoryById(id)) {
            return Result.ok("删除成功");
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 通过id更新分类信息
     * @param category 要更新的分类信息
     * @return Result
     */
    @PutMapping("/category")
    @ResponseBody
    public Result updateCategory(@RequestBody Category category) {
        if(categoryService.updateCategory(category)) {
            return Result.ok("修改成功");
        }else {
            return Result.error("服务器错误");
        }
    }
}