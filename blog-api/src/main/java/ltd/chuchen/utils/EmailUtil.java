package ltd.chuchen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author chuchen
 * @Date 2022/5/9
 * @Description 邮件工具类
 */
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    /**
     * 发送简单邮件消息
     * @param message 发送消息
     * @param subject 主题
     * @param receiver 接受者
     * @return 是否发送成功
     */
    public boolean sendEmil(String message,String subject,String receiver) throws MessagingException {
        return sendEmil(message,subject,receiver,"2665300871@qq.com");
    }

    /**
     * 发送简单邮件消息
     * @param sender 发送者邮箱
     * @param message 发送消息
     * @param subject 主题
     * @param receiver 接受者
     * @return 是否发送成功
     */
    public boolean sendEmil(String message,String subject,String receiver,String sender) throws MessagingException {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(subject); //主题
        helper.setText(message,true);
        helper.setTo(receiver);
        helper.setFrom(sender);
        try {
            javaMailSenderImpl.send(mimeMessage);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
