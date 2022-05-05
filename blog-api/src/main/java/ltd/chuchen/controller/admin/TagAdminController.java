package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.enums.VisitBehavior;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/15
 * @Description 标签控制层
 */

@Controller
@RequestMapping("/api/admin")
public class TagAdminController {

    @Autowired
    private TagService tagService;

    /**
     * 增加标签
     * @param tag 标签对象
     * @return Result
     */
    @OperationLogger("添加标签")
    @PostMapping("/tag")
    @ResponseBody
    public Result saveTag(@RequestBody Tag tag) {
        Integer integer = tagService.saveTag(tag);
        if(integer == 1) {
            return Result.create(202,"标签已存在");
        }else if(integer ==0) {
            return Result.ok("添加成功");
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 修改 tag
     * @param tag 标签对象
     * @return Result
     */
    @OperationLogger("修改标签")
    @PutMapping("/tag")
    @ResponseBody
    public Result updateTag(@RequestBody Tag tag) {
        if(tagService.updateTag(tag)){
            return Result.ok("修改成功");
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 通过 id 删除标签
     * @param id id
     * @return Result
     */
    @OperationLogger("删除标签")
    @DeleteMapping("/tag")
    @ResponseBody
    public Result deleteTag(@RequestParam Long id) {
        if(tagService.deleteTagById(id)) {
            return Result.ok("删除成功");
        }else {
            return Result.error("服务器错误");
        }
    }

    /**
     * 查找所有标签信息
     * @return List<Tag>
     */
    @VisitLogger(VisitBehavior.TAG)
    @GetMapping("/tags")
    @ResponseBody
    public Result getTags() {
        List<Tag> tagList = tagService.getTagList();
        if(tagList.size() != 0) {
            return Result.ok("查找成功",tagList);
        }else {
            return Result.create(202,"未查到信息");
        }
    }
}
