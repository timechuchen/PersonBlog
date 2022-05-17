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
 * @Date 2022/5/17
 * @Description TagCloudView
 */
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class TagCloudView {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;
    private String url;
    private int size;
    private String color;
}
