package ltd.chuchen;

import ltd.chuchen.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

/**
 * @Author chuchen
 * @Date 2022/5/9
 * @Description 邮件测试类
 */
@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailUtil emailUtil;

    @Test
    public void sendEmail() {
        int code = 6656;
        String email = "2665300871@qq.com";
        String subject = "初晨博客验证码";
        String context = "您在初晨博客的验证码是<spin style='color: red; font-weight: 700; font-size: 20px;'>"+code+"</spin>，请再3分钟内完成注册";
        try {
            emailUtil.sendEmil(context,subject,email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
