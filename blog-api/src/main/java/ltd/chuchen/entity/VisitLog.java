package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问日志
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class VisitLog {
    //设置主键 id 的自动设置的方式 默认是雪花算法
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String uuid;//访客标识码
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String behavior;//访问行为
    private String content;//访问内容
    private String remark;//备注
//    private String ip;//ip
//    private String ipSource;//ip来源
    private String os;//操作系统
//    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;
}
