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
 * @Description 登陆日志
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class LoginLog {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;//用户名称
    private String os;//操作系统
    private String browser;//浏览器
    private Boolean status;//登录状态
    private String description;//操作信息
    private String userAgent;
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;


    public LoginLog(String username, boolean status, String description, String userAgent) {
        this.username = username;
        this.status = status;
        this.description = description;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }
}
