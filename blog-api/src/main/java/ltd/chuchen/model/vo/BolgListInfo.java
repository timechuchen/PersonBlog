package ltd.chuchen.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ltd.chuchen.entity.Category;
import ltd.chuchen.entity.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/17
 * @Description 用于返回给前端的不包括具体内容的博客信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class BolgListInfo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;//文章标题
    private Boolean published; //是否公开
    private String category;//文章分类
    private Boolean recommend;//推荐开关
    private Boolean top;//是否置顶
    private String password;//密码保护
    private Boolean appreciation; //赞赏开关
    private Boolean commentEnabled; //评论可见
    private Date createTime;
    private Date updateTime;

}
