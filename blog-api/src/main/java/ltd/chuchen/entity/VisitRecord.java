package ltd.chuchen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 访问记录
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitRecord {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private Integer pv;//访问量
    private Integer uv;//独立用户
    private String date;//日期

    public VisitRecord(Integer pv, Integer uv, String date) {
        this.pv = pv;
        this.uv = uv;
        this.date = date;
    }
}
