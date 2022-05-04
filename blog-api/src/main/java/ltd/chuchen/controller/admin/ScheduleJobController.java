package ltd.chuchen.controller.admin;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.task.VisitorSyncScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 定时任务总管理
 */
@Controller
@RequestMapping("/api/admin")
public class ScheduleJobController {

    @Autowired
    private VisitorSyncScheduleTask visitorSyncScheduleTask;

    @ResponseBody
    @PutMapping("/syncVisitInfoToDatabase")
    public Result syncVisitInfoToDatabase() {
        try{
            visitorSyncScheduleTask.syncVisitInfoToDatabase();
            return Result.ok("执行成功");
        }catch (Exception e) {
            return Result.error("执行失败");
        }
    }

    @ResponseBody
    @DeleteMapping("/delVisitInfoToDatabase")
    public Result delVisitInfoToDatabase() {
        try{
            visitorSyncScheduleTask.delVisitLogInfo();
            return Result.ok("执行成功");
        }catch (Exception e) {
            return Result.error("执行失败");
        }
    }
}
