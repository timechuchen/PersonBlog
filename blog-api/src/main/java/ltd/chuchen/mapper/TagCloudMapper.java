package ltd.chuchen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.chuchen.entity.TagCloud;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chuchen
 * @Date 2022/5/17
 * @Description 标签云持久层
 */
@Mapper
@Repository
public interface TagCloudMapper extends BaseMapper<TagCloud>{

}
