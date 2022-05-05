package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.model.vo.ExceptionLogView;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 异常日志控制层
 */
@Controller
@RequestMapping("/api/admin")
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @GetMapping("/exceptionLogs")
    @ResponseBody
    public Result getExceptionLogList() {
        List<ExceptionLogView> exceptionLogList = exceptionLogService.getExceptionLogList();
        return Result.ok("成功",exceptionLogList);
    }

    @OperationLogger("删除异常日志")
    @DeleteMapping("/exceptionLog")
    @ResponseBody
    public Result deleteExceptionLogById(@RequestParam("id") Long id) {
        boolean b = exceptionLogService.deleteExceptionLogById(id);
        if(b) {
            return Result.ok("删除成功");
        }
        return Result.error("服务器错误");
    }
}
