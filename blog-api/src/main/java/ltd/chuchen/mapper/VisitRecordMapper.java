package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 访问记录持久层接口
 */
@Mapper
@Repository
public interface VisitRecordMapper  extends BaseMapper<VisitRecord> {
    /**
     * 按天数查询访问记录
     */
    @Select("select * from visit_record order by id desc limit #{limit}")
    List<VisitRecord> getVisitRecordListByLimit(Integer limit);
}
