package ltd.chuchen.controller.admin;

import ltd.chuchen.entity.Admin;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.AdminService;
import ltd.chuchen.utils.JWTUtil;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private RedisUtil redisUtil;

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
