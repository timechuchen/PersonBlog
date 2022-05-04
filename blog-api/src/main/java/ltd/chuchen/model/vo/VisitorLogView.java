package ltd.chuchen.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问日志后台展示信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class VisitorLogView {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String uuid;
    private String method;
    private String uri;
    private String remark;
    private Date createTime;
    private String behavior;
    private String content;
    private int times;

}
