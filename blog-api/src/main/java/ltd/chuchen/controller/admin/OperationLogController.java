package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.model.vo.OperationLogView;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 操作日志控制层
 */
@Controller
@RequestMapping("/api/admin")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @GetMapping("/operationLogs")
    @ResponseBody
    public Result getOperationLogList() {
        List<OperationLogView> operationLogList = operationLogService.getOperationLogList();
        return Result.ok("成功",operationLogList);
    }

    @OperationLogger("删除操作日志")
    @DeleteMapping("/operationLog")
    @ResponseBody
    public Result deleteOperationLogById(@RequestParam("id") Long id) {
        boolean b = operationLogService.deleteOperationLogById(id);
        if(b) {
            return Result.ok("删除成功");
        }
        return Result.error("服务器错误");
    }
}
