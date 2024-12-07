package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.BondpairPet;

/**
 * Mapper interface for performing CRUD operations on the BondpairPet entity.
 * Extends the BaseMapper interface from MyBatis-Plus to provide common database operations.
 * This interface provides methods to interact with the BondpairPet table in the database.
 *
 * @see BaseMapper
 */
@Mapper
public interface BondpairPetMapper extends BaseMapper<BondpairPet> {
}
