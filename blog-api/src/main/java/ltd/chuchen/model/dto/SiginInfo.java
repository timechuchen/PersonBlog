package ltd.chuchen.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  注册信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SiginInfo {
    private String username;
    private String password;
    private String phone;
}
