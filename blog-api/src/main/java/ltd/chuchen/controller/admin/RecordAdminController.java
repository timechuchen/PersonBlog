package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.entity.Record;
import ltd.chuchen.enums.VisitBehavior;
import ltd.chuchen.model.dto.RecordInfo;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 动态后台管理
 * @Author: Chuchen
 * @Date: 2020-08-12
 */
@Controller
@RequestMapping("/api/admin")
public class RecordAdminController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/record")
    @ResponseBody
    public Result saveRecord(@RequestBody RecordInfo record) {
        if(recordService.saveMoment(record)){
            return Result.ok("添加成功");
        }else {
            return Result.error("添加失败");
        }
    }

    @DeleteMapping ("/record")
    @ResponseBody
    public Result deleteRecordById(@RequestParam Long id) {
        if(recordService.deleteRecordById(id)){
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @GetMapping ("/record")
    @ResponseBody
    public Result getRecordById(@RequestParam Long id) {
        Record record = recordService.getRecordById(id);
        if(record == null) {
            return Result.create(202,"未找到");
        }
        return Result.ok("获取成功",record);
    }

    @PutMapping ("/record")
    @ResponseBody
    public Result updateRecord(@RequestBody Record record) {
        if(recordService.updateRecord(record)){
            return Result.ok("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }

    @VisitLogger(VisitBehavior.COMMENT)
    @GetMapping("/records")
    @ResponseBody
    public Result getRecordListAll() {
        List<Record> records = recordService.selectAll();
        return Result.ok("成功",records);
    }

    @PutMapping ("/published")
    @ResponseBody
    public Result updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
        Boolean aBoolean = recordService.updateMomentPublishedById(id, published);
        if(aBoolean) {
            return Result.ok("操作成功");
        }else {
            return Result.error("操作失败");
        }
    }
}
