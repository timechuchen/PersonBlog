package ltd.chuchen;

import ltd.chuchen.entity.Tag;
import ltd.chuchen.entity.User;
import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.service.UserService;
import ltd.chuchen.service.impl.TagServiceImpl;
import ltd.chuchen.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BlogApiApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        int i = userMapper.deleteById(1504321767206895617L);
    }

    @Test
    public void testInsert() {
        System.out.println(userService.findUserByPhone("123456789878"));
    }

    @Test
    public void testSelect(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Kfb200004195013"));
    }
}
