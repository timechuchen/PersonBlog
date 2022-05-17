package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.ExceptionLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 异常日志映射文件
 */
@Mapper
@Repository
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {

    @Delete("truncate table exception_log")
    void emptyExceptionLog();
}
