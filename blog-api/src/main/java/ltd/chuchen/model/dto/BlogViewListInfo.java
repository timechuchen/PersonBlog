package ltd.chuchen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ltd.chuchen.entity.Tag;

import java.util.Date;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/18
 * @Description 用于前台展示博客的列表信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class BlogViewListInfo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogId;
    private String blogTitle;
    private final String author = "初晨";
    private String blogPic;
    private List<Tag> blogTage;
    private Date updateTime;
    private Integer comment;
    private String description;
    private Boolean published;
    private String password;

}
