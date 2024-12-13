package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.Pet;

/**
 * Mapper interface for the {@link cocoa.entity.Pet} entity.
 * Extends the MyBatis-Plus {@link com.baomidou.mybatisplus.core.mapper.BaseMapper} to provide
 * CRUD operations for the Pet entity.
 */
@Mapper
public interface PetMapper extends BaseMapper<Pet> {
}
