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
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/17
 * @Description 用于给前台前敌一个博客的详细信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class BlogInfos {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;//文章标题
    private String firstPicture; //首图URL
    private String description;
    private String content;
    private Integer words;
    private Integer readTime;
    private Integer views;
    private Boolean published; //是否公开
    private Boolean recommend;//推荐开关
    private Boolean top;//是否置顶
    private String password;//密码保护
    private Boolean appreciation; //赞赏开关
    private Boolean commentEnabled; //评论可见

    private Category category;//文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签

    private Object cate;//页面展示层传输的分类对象
    private List<Object> tagList;

}
