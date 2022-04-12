package ltd.chuchen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author chuchen
 * @Date 2022/4/12
 * @Description 用于接受后台传过来的要修改的评论信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentUpdate {
    private String id;
    private String nickname;
    private String avatar;
    private String email;
    private String content;
}
