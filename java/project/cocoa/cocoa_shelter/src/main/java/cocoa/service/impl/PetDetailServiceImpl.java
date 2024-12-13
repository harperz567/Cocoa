package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cocoa.entity.PetDetail;
import cocoa.mapper.PetDetailMapper;
import cocoa.service.PetDetailService;

/**
 * Implementation of the {@link cocoa.service.PetDetailService} interface.
 * Extends the MyBatis-Plus {@link com.baomidou.mybatisplus.extension.service.impl.ServiceImpl} class
 * to provide default implementation for CRUD operations on the PetDetail entity.
 */
@Service
public class PetDetailServiceImpl extends ServiceImpl<PetDetailMapper, PetDetail> implements
    PetDetailService {

}
