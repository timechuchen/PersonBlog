package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.Admin;
import ltd.chuchen.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author chuchen
 * @Date 2022/3/20
 * @Description TODO
 */
@Mapper
@Component
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
