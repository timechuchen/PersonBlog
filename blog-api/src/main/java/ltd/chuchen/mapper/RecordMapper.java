package ltd.chuchen.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
@Repository
public interface RecordMapper extends BaseMapper<Record> {

    @Select("select * from cblog.record where deleted = 0")
    List<Record> selectAll();
}
