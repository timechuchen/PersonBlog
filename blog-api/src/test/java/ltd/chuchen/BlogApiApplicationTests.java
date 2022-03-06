package ltd.chuchen;

import ltd.chuchen.entity.User;
import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.service.UserService;
import ltd.chuchen.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        int i = userMapper.deleteById(1499777449649516546L);
    }

    @Test
    public void testInsert() {
        System.out.println(userService.findUserByPhone("123456789878"));
    }

    @Test
    public void testSelect(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("username","初晨");
        map.put("password","1234");
        List<User> users = userMapper.selectByMap(map);
        if(users.size() != 0){
            users.forEach(System.out::println);
            System.out.println(users);
        }else {
            System.out.println("未找到");
        }
    }


}
