package ltd.chuchen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 用于接受前台返回的评论
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentInfo {

    private String blogId;
    private String authorId;
    private short parentCommentId;
    private short page;
    private String context;
}
