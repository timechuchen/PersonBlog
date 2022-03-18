package ltd.chuchen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ltd.chuchen.entity.Category;
import ltd.chuchen.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/16
 * @Description 博客简单类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogInfo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;//文章标题
    private String firstPicture;//文章首图，用于随机文章展示
    private String content;//文章正文
    private String description;//描述
    private Boolean published;//公开或私密
    private Boolean recommend;//推荐开关
    private Boolean appreciation;//赞赏开关
    private Boolean commentEnabled;//评论开关
    private Boolean top;//是否置顶
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private Integer readTime;//阅读时长(分钟)
    private String password;//密码保护

    private Category category;//文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签

    private String cate;//页面展示层传输的分类对象：正常情况下为 字符串 或 分类id
    private List<String> tagList;//页面展示层传输的标签对象：正常情况下为 List<Integer>标签id 或 List<String>标签名
}
