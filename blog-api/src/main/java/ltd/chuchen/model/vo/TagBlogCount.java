package ltd.chuchen.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 标签和博客数量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class TagBlogCount {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;//分类名
    private Integer value;//分类下博客数量
}
