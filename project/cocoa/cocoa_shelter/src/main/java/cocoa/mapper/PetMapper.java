package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.Pet;

@Mapper
public interface PetMapper extends BaseMapper<Pet> {
}
