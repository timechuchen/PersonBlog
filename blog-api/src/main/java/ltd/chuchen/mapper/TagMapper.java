package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/3/15
 * @Description Tag 的 Mapper 映射
 */
@Mapper
@Component
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select * from cblog.tag")
    List<Tag> selectAll();
}
