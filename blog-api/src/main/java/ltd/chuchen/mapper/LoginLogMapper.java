package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 登陆日志映射文件
 */
@Mapper
@Component
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
