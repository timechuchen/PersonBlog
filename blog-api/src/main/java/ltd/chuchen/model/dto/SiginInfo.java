package ltd.chuchen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 *  注册信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiginInfo {
    private String username;
    private String password;
    private String email;
    private String code;
    private String imageUrl;
}
