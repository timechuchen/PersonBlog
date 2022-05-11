package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
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

    @OperationLogger("手动执行定时任务（同步访客信息到数据库）")
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

    @OperationLogger("手动执行定时任务（删除浏览记录）")
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

    @OperationLogger("手动执行定时任务（清空异常日志）")
    @ResponseBody
    @DeleteMapping("/emptyExceptionLog")
    public Result emptyExceptionLog() {
        try{
            visitorSyncScheduleTask.emptyExceptionLog();
            return Result.ok("执行成功");
        }catch (Exception e) {
            return Result.error("执行失败");
        }
    }

    @OperationLogger("手动执行定时任务（更新动态的点赞数）")
    @PutMapping("/updateLikesOfDB")
    @ResponseBody
    public Result updateLikesOfDB() {
        try{
            visitorSyncScheduleTask.updateRecordLikes();
            return Result.ok("执行成功");
        }catch (Exception e) {
            return Result.error("执行失败");
        }
    }
}
