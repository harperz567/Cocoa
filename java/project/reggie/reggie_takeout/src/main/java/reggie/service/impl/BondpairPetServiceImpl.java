package reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reggie.entity.BondpairPet;
import reggie.mapper.BondpairPetMapper;
import reggie.service.BondpairPetService;

@Service
@Slf4j
public class BondpairPetServiceImpl extends ServiceImpl<BondpairPetMapper, BondpairPet> implements
    BondpairPetService {


}
