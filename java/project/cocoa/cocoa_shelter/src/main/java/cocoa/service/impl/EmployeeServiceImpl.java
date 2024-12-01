package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cocoa.mapper.EmployeeMapper;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, cocoa.entity.Employee> implements
    cocoa.service.EmployeeService {

}
