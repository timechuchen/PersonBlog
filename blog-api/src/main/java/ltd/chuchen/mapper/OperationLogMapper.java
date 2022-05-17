package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.OperationLog;
import ltd.chuchen.entity.VisitLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 操作日志映射文件
 */
@Mapper
@Repository
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
