package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @Date 2022/5/5
 * @Description 操作日志
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class OperationLog {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;//操作者用户名
    private String uri;//请求接口
    private String method;//请求方式
    private String description;//操作描述
    private String os;//操作系统
    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    private String userAgent;
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;

    public OperationLog(String username, String uri, String method, String description,Integer times,String userAgent) {
        this.username = username;
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.times = times;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }
}
