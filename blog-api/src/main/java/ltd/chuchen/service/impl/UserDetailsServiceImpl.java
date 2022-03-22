package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.entity.Admin;
import ltd.chuchen.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author chuchen
 * @Date 2022/3/22
 * @Description 实现 SpringSecurity 中默认的验证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if(admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return admin;
    }
}
