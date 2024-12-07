package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cocoa.entity.BondpairPet;
import cocoa.mapper.BondpairPetMapper;
import cocoa.service.BondpairPetService;

/**
 * Service implementation for handling operations related to BondpairPet entities.
 * Extends ServiceImpl and implements BondpairPetService interface.
 * Provides business logic for interacting with the BondpairPet entity.
 *
 * @see BondpairPetService
 */
@Service
@Slf4j
public class BondpairPetServiceImpl extends ServiceImpl<BondpairPetMapper, BondpairPet> implements
    BondpairPetService {


}
