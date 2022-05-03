package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.entity.Admin;
import ltd.chuchen.mapper.AdminMapper;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.AdminService;
import ltd.chuchen.utils.HashUtils;
import ltd.chuchen.utils.JWTUtil;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author chuchen
 * @Date 2022/3/20
 * @Description 管理员服务层
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if(admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(!HashUtils.matchBC(password, admin.getPassword())) {
            throw new UsernameNotFoundException("密码错误");
        }
        return admin;
    }

    @Override
    public Admin findAdminById(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public Result login(Admin admin) {
        //AuthenticationManager authentication 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没有通过给出对应提示
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //通过则使用用户id生成jwt，并且将完整的用户信息放入redis
        Admin loginAdmin = (Admin)authenticate.getPrincipal();
        Long id = loginAdmin.getId();
        Map<String,String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        String token = JWTUtil.getToken(map, 7);
        redisUtil.set(String.valueOf(id),loginAdmin);
        Map<String,Object> res = new HashMap<>();
        res.put("token",token);
        res.put("user",loginAdmin);
        return Result.ok("登陆成功",res);
    }

    @Override
    public Result logout() {
        //获取 SecurityContextHolder 的用户 id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin)authentication.getPrincipal();
        Long id = admin.getId();
        //删除 redis 中的值
        redisUtil.del(String.valueOf(id));
        return Result.ok("退出成功");
    }
}
