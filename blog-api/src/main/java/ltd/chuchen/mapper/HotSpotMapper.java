package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.HotSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface HotSpotMapper extends BaseMapper<HotSpot> {
    //清空指定表
    @Update("truncate table cblog.hot_spot")
    void deleteHotSpot();

    @Select("select * from cblog.hot_spot")
    List<HotSpot> selectAll();
}
