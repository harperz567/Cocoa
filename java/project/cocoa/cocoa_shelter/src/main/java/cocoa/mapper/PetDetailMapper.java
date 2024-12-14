package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.PetDetail;
/**
 * PetDetailMapper is an interface that extends MyBatis-Plus's BaseMapper.
 * It provides basic CRUD operations for the PetDetail entity without requiring additional SQL or implementation.
 *
 * MyBatis-Plus automatically generates implementations for methods in BaseMapper,
 * such as insert, delete, update, and select operations.
 */
@Mapper
public interface PetDetailMapper extends BaseMapper<PetDetail> {

}
