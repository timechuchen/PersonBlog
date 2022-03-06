package ltd.chuchen.controller;

import ltd.chuchen.entity.User;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.UserService;
import ltd.chuchen.utils.RedisUtil;
import ltd.chuchen.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class LogoController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    private Integer code = 0;

    @ResponseBody
    @PostMapping("/user/sigin/{username}/{password}/{phone}/{code}")
    public Result sigin(@PathVariable String code, @PathVariable String password, @PathVariable String phone, @PathVariable String username) {
        if(Integer.parseInt(code) != this.code){
            return Result.create(70001,"验证码不对");
        }
        int res = userService.sigin(username,password,phone,code);
        if(res == 0){
            return Result.ok("注册成功");
        }else if (res == 1){
            return Result.create(20005,"用户已存在！");
        }else {
            return Result.create(500,"注册异常");
        }
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        //查询是否存在该用户
        boolean isLoginSuccessfully = userService.login(phone, password);
        User loginInfo = userService.findUserByPhone(phone);
        if(isLoginSuccessfully){
            //生成一个 token 令牌
            String token = TokenUtil.getToken();
            //存到Redis中
            redisUtil.set(token,loginInfo,3000000L);
            return Result.ok("登陆成功",token);
        }else if(loginInfo != null){
            //告诉用户登陆失败
            return Result.create(20003,"密码错误");
        }else {
            return Result.create(20001,"用户不存在");
        }
    }

    @ResponseBody
    @RequestMapping("/user/getCode/{phone}")
    public Result getCode(@PathVariable String phone){
        //创建一个随机的验证码
        int r = (int)(Math.random()*8998)+1000+1;
        this.code =  r;
        return Result.ok("返回成功", "电话"+phone+"的验证码是："+r);
    }

    @ResponseBody
    @RequestMapping("/user/logout")
    public Result logout(HttpServletRequest request){
        //退出登录的逻辑代码
        String token = request.getHeader("token");
        if(token.equals("")){
            return Result.error("未登录");
        }
        //删除redis中的对应令牌
        redisUtil.del(token);
        return Result.ok("退出成功");
    }

    @ResponseBody
    @GetMapping("/user/getUserLogin")
    public Result getUserAllLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object user = redisUtil.get(token);
        if(user != null){
            return Result.ok("获取登陆用户成功",user);
        }else {
            return Result.error("登陆已过期");
        }
    }
}
