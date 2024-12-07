package cocoa.service.impl;

import cocoa.dto.PetDto;
import cocoa.entity.PetDetail;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cocoa.common.CustomException;
import cocoa.dto.BondpairDto;
import cocoa.entity.Bondpair;
import cocoa.entity.BondpairPet;
import cocoa.mapper.BondpairMapper;
import cocoa.service.BondpairPetService;
import cocoa.service.BondpairService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for handling operations related to Bondpair entities.
 * Extends ServiceImpl and implements BondpairService interface.
 * Provides business logic for saving, deleting, and retrieving Bondpair information
 * and related BondpairPet data.
 */
@Service
@Slf4j
public class BondpairServiceImpl extends ServiceImpl<BondpairMapper, Bondpair> implements
    BondpairService {
    @Autowired
    private BondpairPetService bondpairPetService;

    /**
     * Saves a Bondpair along with its associated BondpairPet records.
     * The Bondpair is saved first, followed by the related BondpairPets,
     * with the Bondpair ID being set for each BondpairPet.
     *
     * @param bondpairDto The BondpairDto object containing the Bondpair and BondpairPets data to be saved.
     */
    public void saveWithPet(BondpairDto bondpairDto){
        // Save family basic info, update bondpair sheet, execute insert
        this.save(bondpairDto);

        List<BondpairPet> bondpairPets = bondpairDto.getBondpairPets();
        bondpairPets.stream().map((item) ->{
            item.setBondpairId(bondpairDto.getId());
            return item;
        }).collect(Collectors.toList());

        // Save family and animal relation info, insert in bondpair-pet sheet
        bondpairPetService.saveBatch(bondpairPets);
    }

    /**
     * Removes a Bondpair and its related BondpairPet records.
     * Before deletion, the adoption status of the Bondpair is checked to ensure it can be deleted.
     * If the Bondpair is not adopted (status = 1), an exception is thrown.
     *
     * @param ids A list of Bondpair IDs to be deleted.
     * @throws CustomException if the Bondpair is not adopted, preventing deletion.
     */
    public void removeWithPet(List<Long> ids){
        // SQL: Select count(*) from bondpair where id in (1, 2, 3) and status = 1
        // Check adoption status to see if we can delete that family
        LambdaQueryWrapper<Bondpair> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Bondpair::getId, ids);
        queryWrapper.eq(Bondpair::getStatus, 1);

        long count = this.count(queryWrapper);
        if (count > 0) {
            // If we can not delete, throw an error
            throw new CustomException("Family are not adopted, can not delete");
        }

        // If we can delete, first we delete the data in family sheet
        this.removeByIds(ids);

        // SQL: Delete from bondpair-pet where bondpair id in (1, 2, 3)
        LambdaQueryWrapper<BondpairPet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(BondpairPet::getBondpairId, ids);
        // Delete relations sheet(bondpair-pet)
        bondpairPetService.remove(lambdaQueryWrapper);
    }

    /**
     * Retrieves detailed information for a specific Bondpair, including its associated BondpairPet records.
     *
     * @param id The ID of the Bondpair to retrieve.
     * @return A BondpairDto containing the Bondpair data and its related BondpairPets, or null if not found.
     */
    @Override
    public BondpairDto getBondpairData(Long id) {
        Bondpair bondpair = this.getById(id);
        BondpairDto bondpairDto = new BondpairDto();

        LambdaQueryWrapper<BondpairPet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null,BondpairPet::getBondpairId,id);

        if (bondpair != null){
            BeanUtils.copyProperties(bondpair,bondpairDto);

            List<BondpairPet> dishes = bondpairPetService.list(queryWrapper);
            bondpairDto.setBondpairPets(dishes);

            return bondpairDto;
        }

        return null;
    }
}