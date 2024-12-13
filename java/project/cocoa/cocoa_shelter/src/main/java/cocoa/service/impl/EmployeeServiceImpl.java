package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cocoa.mapper.EmployeeMapper;

/**
 * Implementation of the {@link cocoa.service.EmployeeService} interface.
 * Extends the MyBatis-Plus {@link com.baomidou.mybatisplus.extension.service.impl.ServiceImpl} class
 * to provide default implementation for CRUD operations on the Employee entity.
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, cocoa.entity.Employee> implements
    cocoa.service.EmployeeService {

}
