package cocoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cocoa.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
