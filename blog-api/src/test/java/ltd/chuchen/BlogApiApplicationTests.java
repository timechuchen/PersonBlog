package ltd.chuchen;

import ltd.chuchen.mapper.UserMapper;
import ltd.chuchen.service.UserService;
import ltd.chuchen.service.impl.RecordServiceImpl;
import ltd.chuchen.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class BlogApiApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        int i = userMapper.deleteById(1504321767206895617L);
    }


    @Test
    public void testSelect(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Kfb200004195013"));
    }

    @Test
    void contextLoads2() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("小初晨");
        helper.setText("<p style='color: red'>谢谢你</p>",true);
        //附件
//        helper.addAttachment("1.png",new File("F:\\Users\\Administrator\\Desktop\\1.png"));
//        helper.addAttachment("2.pjg",new File("F:\\Users\\Administrator\\Desktop\\1.png"));
        helper.setTo("2665300871@qq.com");
        helper.setFrom("2665300871@qq.com");
        mailSender.send(mimeMessage);
    }
}
