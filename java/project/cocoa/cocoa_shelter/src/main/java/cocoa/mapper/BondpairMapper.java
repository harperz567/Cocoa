package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cocoa.entity.Bondpair;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper interface for performing CRUD operations on the Bondpair entity.
 * Extends the BaseMapper interface from MyBatis-Plus to provide common database operations.
 * This interface provides methods to interact with the Bondpair table in the database.
 */
@Mapper
public interface BondpairMapper extends BaseMapper<Bondpair> {

}
