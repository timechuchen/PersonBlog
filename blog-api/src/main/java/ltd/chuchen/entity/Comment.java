package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ltd.chuchen.model.vo.BlogIdAndTitle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/23
 * @Description 评论实体类
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true) //支持链式编程
public class Comment {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String nickname;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//评论时间
    private String ip;//评论者ip地址
    private Boolean isPublished;//公开或回收站
    private Boolean isAdminComment;//博主回复
    private Short page;//0普通文章，1关于我页面
    private Boolean isNotice;//接收邮件提醒
    private Long parentCommentId;//父评论id 默认-1为根评论
    @TableLogic //逻辑删除
    private Short deleted;
    @Version //乐观锁
    private Integer version;
    private String blogId;//所属的文章
    @TableField(exist = false)
    private List<Comment> replyComments = new ArrayList<>();//回复该评论的评论
}
