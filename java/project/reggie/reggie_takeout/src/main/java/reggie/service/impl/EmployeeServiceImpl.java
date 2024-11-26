package reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import reggie.mapper.EmployeeMapper;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, reggie.entity.Employee> implements
    reggie.service.EmployeeService {

}
