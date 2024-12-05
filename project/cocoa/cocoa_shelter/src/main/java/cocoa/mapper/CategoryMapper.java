package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.Category;

/**
 * An interface for handling database operations related to Category entity.
 * It extends the BaseMapper interface from MyBatis-Plus, which provides
 * built-in methods for CRUD.
 *
 * @Mapper annotation marks it as a MyBatis mapper interface, so it will be
 * recognized and managed by MyBatis for SQL mapping.
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
