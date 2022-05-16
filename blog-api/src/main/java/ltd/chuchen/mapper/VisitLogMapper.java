package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.VisitLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问日志映射文件
 */
@Mapper
@Repository
public interface VisitLogMapper extends BaseMapper<VisitLog> {
    //清空表的所有数据
    @Delete("truncate table visit_log")
    void emptyVisitorLog();

    @Select("select count(*) from visit_log where date(create_time)=curdate()")
    int countVisitLogByToday();
}
