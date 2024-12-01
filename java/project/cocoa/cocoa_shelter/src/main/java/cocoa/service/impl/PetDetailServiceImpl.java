package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cocoa.entity.PetDetail;
import cocoa.mapper.PetDetailMapper;
import cocoa.service.PetDetailService;

@Service
public class PetDetailServiceImpl extends ServiceImpl<PetDetailMapper, PetDetail> implements
    PetDetailService {

}
