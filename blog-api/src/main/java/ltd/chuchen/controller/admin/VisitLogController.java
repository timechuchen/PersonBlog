package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.enums.VisitBehavior;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.model.vo.VisitorLogView;
import ltd.chuchen.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description TODO
 */
@Controller
@RequestMapping("/api/admin")
public class VisitLogController {

    @Autowired
    private VisitLogService visitLogService;

    @VisitLogger(VisitBehavior.CHECK_LOG)
    @GetMapping("/visitLogs")
    @ResponseBody
    public Result getVisitLogList() {
        List<VisitorLogView> visitorLogViews = visitLogService.getVisitLogList();
        return Result.ok("成功",visitorLogViews);
    }

    @DeleteMapping("/visitLog")
    @ResponseBody
    public Result delVisitById(@RequestParam("id") Long id) {
        if(visitLogService.deleteVisitLogById(id)) {
            return Result.ok("删除成功");
        }else {
            return Result.error("服务器错误");
        }
    }
}
