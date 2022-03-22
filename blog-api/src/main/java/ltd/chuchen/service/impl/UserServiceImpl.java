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
    public boolean login(String username, String password) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",username);
        map.put("password",password);
        List<User> users = userMapper.selectByMap(map);
        return users.size() != 0;
    }

    @Override
    public int sigin(String username, String password, String phone,String code,String imageUrl) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        List<User> users = userMapper.selectByMap(map);
        if(users.size() == 0){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
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
    public User findUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        return userMapper.selectOne(queryWrapper);
    }
}
