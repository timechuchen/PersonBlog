package ltd.chuchen.model.dto;

import lombok.*;

/**
 *  登录账号密码
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginInfo {
    private String username;
    private String token;
}
