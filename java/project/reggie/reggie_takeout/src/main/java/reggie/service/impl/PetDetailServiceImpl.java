package reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import reggie.entity.PetDetail;
import reggie.mapper.PetDetailMapper;
import reggie.service.PetDetailService;

@Service
public class PetDetailServiceImpl extends ServiceImpl<PetDetailMapper, PetDetail> implements
    PetDetailService {

}
