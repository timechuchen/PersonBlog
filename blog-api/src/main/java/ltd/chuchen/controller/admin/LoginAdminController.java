package ltd.chuchen.controller.admin;

import ltd.chuchen.entity.Admin;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chuchen
 * @Date 2022/3/20
 * @Description 后台登陆管理
 */
@Controller
@RequestMapping("/api/admin")
public class LoginAdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Admin admin ){
        return adminService.login(admin);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public Result logout(){
        return adminService.logout();
    }
}
