package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
