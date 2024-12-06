package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.User;

/**
 * Mapper interface for User entities.
 * Provides database operations for the User table using MyBatis-Plus.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
