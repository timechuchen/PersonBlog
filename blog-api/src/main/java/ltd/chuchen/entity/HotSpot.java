package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotSpot {
    //设置主键 id 的自动设置的方式 默认是雪花算法
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String role;
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;
}
