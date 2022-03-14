package ltd.chuchen.controller;

import ltd.chuchen.entity.Record;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @GetMapping("/recordsByDesc")
    @ResponseBody
    public Result getRecordListAllDesc() {
        List<Record> records = recordService.selectAllByDesc();
        return Result.ok("成功",records);
    }
}
