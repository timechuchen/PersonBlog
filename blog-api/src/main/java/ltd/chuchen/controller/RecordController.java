package ltd.chuchen.controller;

import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.entity.Record;
import ltd.chuchen.enums.VisitBehavior;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/records")
    @ResponseBody
    public Result getRecordListAll() {
        List<Record> records = recordService.selectAll();
        return Result.ok("成功",records);
    }

    @VisitLogger(VisitBehavior.MOMENT)
    @GetMapping("/recordsByDesc")
    @ResponseBody
    public Result getRecordListAllDesc() {
        List<Record> records = recordService.selectAllByDesc();
        return Result.ok("成功",records);
    }

    @GetMapping("/likesOfRecord")
    @ResponseBody
    public Result getLikesOfRecord() {
        Map<String, Integer> likesOfRecord = recordService.getLikesOfRecord();
        return Result.ok("ok",likesOfRecord);
    }

    @GetMapping("/addLikes")
    @ResponseBody
    public Result addLike(@RequestParam("recordId") String recordId) {
        recordService.updateLikes(recordId);
        return Result.ok("ok");
    }
}
