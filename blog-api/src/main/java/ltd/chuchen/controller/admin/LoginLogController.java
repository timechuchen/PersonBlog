package ltd.chuchen.controller.admin;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.model.vo.LoginLogView;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 登陆日志控制层
 */
@Controller
@RequestMapping("/api/admin")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("/loginLogs")
    @ResponseBody
    public Result getLoginLogList() {
        List<LoginLogView> loginLogList = loginLogService.getLoginLogList();
        return Result.ok("成功",loginLogList);
    }

    @OperationLogger("删除登陆日志")
    @DeleteMapping("/loginLog")
    @ResponseBody
    public Result deleteLoginLogById(@RequestParam("id") Long id) {
        boolean b = loginLogService.deleteLoginLogById(id);
        if(b) {
            return Result.ok("删除成功");
        }
        return Result.error("服务器错误");
    }
}
