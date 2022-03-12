package ltd.chuchen.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface RecordMapper extends BaseMapper<Record> {
}
