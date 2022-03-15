package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Blog {

    @TableId(type = IdType.ID_WORKER)
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;//更新时间
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private String password;//密码保护

    private Category category;//文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签
    private String role;
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;

}
