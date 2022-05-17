package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.model.vo.TagCloudView;
import ltd.chuchen.service.TagCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/17
 * @Description 标签云控制层
 */
@Controller
@RequestMapping("/api/admin")
public class TagCloudAdminController {

    @Autowired
    private TagCloudService tagCloudService;

    @GetMapping("/tagClouds")
    @ResponseBody
    public Result getAllTagCloud() {
        List<TagCloudView> allTagCloud = tagCloudService.getAllTagCloud();
        if(allTagCloud == null) {
            return Result.error("加载数据失败");
        }
        return Result.ok("ok",allTagCloud);
    }

    @OperationLogger("添加标签云")
    @PostMapping("/tagCloud")
    @ResponseBody
    public Result addTagCloud(@RequestBody TagCloudView tagCloud) {
        boolean b = tagCloudService.addTagCloud(tagCloud);
        if(b) {
            return Result.ok("添加成功");
        }else {
            return Result.error("添加失败");
        }
    }

    @OperationLogger("修改标签云")
    @PutMapping("/tagCloud")
    @ResponseBody
    public Result updateTagCloud(@RequestBody TagCloudView tagCloud) {
        boolean b = tagCloudService.updateTagCloudById(tagCloud);
        if(b) {
            return Result.ok("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

    @OperationLogger("删除标签云")
    @DeleteMapping("/tagCloud")
    @ResponseBody
    public Result deleteTagCloud(@RequestParam Long id) {
        boolean b = tagCloudService.deleteTagCloudById(id);
        if(b) {
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
}
