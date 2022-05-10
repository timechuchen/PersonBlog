package ltd.chuchen.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.User;
import ltd.chuchen.model.dto.SiginInfo;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.UserService;
import ltd.chuchen.utils.EmailUtil;
import ltd.chuchen.utils.JWTUtil;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class LogoController {

    private static final String SUBJECT = "初晨博客注册验证码";

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EmailUtil emailUtil;

    @ResponseBody
    @PostMapping("/user/sigin")
    public Result sigin(@RequestBody SiginInfo siginInfo,HttpServletRequest request) {
        String uuid = request.getHeader("identification");
        boolean b = redisUtil.hHasKey(RedisKeyConstant.EMAIL_CODE, uuid);
        if(!b) {
            return Result.create(201,"验证码已过期");
        }
        if(Integer.parseInt(siginInfo.getCode()) != (int)redisUtil.hget(RedisKeyConstant.EMAIL_CODE, uuid)){
            return Result.create(70001,"验证码错误");
        }
        int res = userService.sigin(siginInfo.getUsername(),siginInfo.getPassword(),siginInfo.getEmail(),siginInfo.getCode(),siginInfo.getImageUrl());
        if(res == 0){
            return Result.ok("注册成功");
        }else if (res == 1){
            return Result.create(20005,"用户已存在！");
        }else {
            return Result.error("注册异常");
        }
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Result login(@RequestParam("email") String email, @RequestParam("password") String password){
        //查询是否存在该用户
        boolean isLoginSuccessfully = userService.login(email, password);
        User loginInfo = userService.findUserByEmail(email);
        if(isLoginSuccessfully){
            Map<String,String> payload = new HashMap<>();
            payload.put("id", String.valueOf(loginInfo.getId()));
            payload.put("role",loginInfo.getRole());
            payload.put("username",loginInfo.getUsername());
            payload.put("avatar",loginInfo.getAvatar());
            //生成一个 JWT 令牌
            String token = JWTUtil.getToken(payload,3);
            //存到Redis中
            redisUtil.set(String.valueOf(loginInfo.getId()),payload);
            return Result.ok("登陆成功",token);
        }else if(loginInfo != null){
            //告诉用户登陆失败
            return Result.create(20003,"密码错误");
        }else {
            return Result.create(20001,"用户不存在");
        }
    }

    @ResponseBody
    @RequestMapping("/user/getCode/{email}")
    public Result getCode(@PathVariable String email,HttpServletRequest request){
        String uuid = request.getHeader("identification");
        //创建一个随机的验证码
        int r = (int)(Math.random()*8998)+1000+1;
        redisUtil.hset(RedisKeyConstant.EMAIL_CODE,uuid,r,120);
        String message = "感谢注册初晨博客，验证码是<spin style='color: red; font-weight: 700; font-size: 20px;'>"+r+"</spin>，请再2分钟内完成注册";
        try {
            emailUtil.sendEmil(message,SUBJECT,email);
        } catch (MessagingException e) {
            return Result.create(201,"验证码获取失败");
        }
        return Result.ok("返回成功");
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
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String id = tokenInfo.getClaim("id").asString();
        redisUtil.del(id);
        return Result.ok("退出成功");
    }

    @ResponseBody
    @GetMapping("/user/getUserLogin")
    public Result getUserAllLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object user = null;
        try{
            DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
            String id = tokenInfo.getClaim("id").asString();
            user = redisUtil.get(id);
        }catch (Exception e) {
            return Result.create(401,"登陆已过期");
        }
        if(user != null){
            return Result.ok("获取登陆用户成功",user);
        }else {
            return Result.error("服务器错误");
        }
    }
}
