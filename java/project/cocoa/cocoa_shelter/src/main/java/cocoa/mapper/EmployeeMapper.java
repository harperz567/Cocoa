package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.Employee;

/**
 * Mapper interface for the {@link cocoa.entity.Employee} entity.
 * Extends the MyBatis-Plus {@link com.baomidou.mybatisplus.core.mapper.BaseMapper} to provide
 * CRUD operations for the Employee entity.
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
