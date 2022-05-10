package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.experimental.Accessors;
import ltd.chuchen.entity.User;
import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String email, String password) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("email",email);
        map.put("password",password);
        List<User> users = userMapper.selectByMap(map);
        return users.size() != 0;
    }

    @Override
    public int sigin(String username, String password, String email,String code,String imageUrl) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> users = userMapper.selectByMap(map);
        if(users.size() == 0){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setAvatar(imageUrl);
            userMapper.insert(user);
            return 0;
        }else if(users.size() == 1) {
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return userMapper.selectOne(queryWrapper);
    }
}
