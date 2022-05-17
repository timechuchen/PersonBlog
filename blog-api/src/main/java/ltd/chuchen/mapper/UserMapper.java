package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
