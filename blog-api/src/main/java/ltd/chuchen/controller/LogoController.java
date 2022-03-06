package ltd.chuchen.controller;

import com.alibaba.fastjson.JSON;
import ltd.chuchen.entity.User;
import ltd.chuchen.model.dto.LoginInfo;
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
            return Result.error("验证码不对");
        }
        if(userService.sigin(username,password,phone,code) == 0){
            return Result.ok("注册成功");
        }else if(userService.sigin(username,password,phone,code) == 1){
            return Result.error("用户已存在！");
        }else {
            return Result.error("注册失败！");
        }
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        //查询是否存在该用户
        boolean isLoginSuccessfully = userService.login(phone, password);
        if(isLoginSuccessfully){
            //生成一个 token 令牌
            String token = TokenUtil.getToken();
            //存到Redis中
            User loginInfo = userService.findUserByPhone(phone);
            redisUtil.set(token,loginInfo,300000L);
            return Result.ok("登陆成功",token);
        }else {
            //告诉用户登陆失败
            return Result.error("登陆失败");
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
    @RequestMapping("/user/logout/{phone}")
    public Result logout(@PathVariable String phone){
        //注销的逻辑代码

        return Result.ok("注销成功");
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
