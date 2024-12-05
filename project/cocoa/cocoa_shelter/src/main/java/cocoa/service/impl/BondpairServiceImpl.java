package cocoa.service.impl;

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

@Service
@Slf4j
public class BondpairServiceImpl extends ServiceImpl<BondpairMapper, Bondpair> implements
    BondpairService {
    @Autowired
    private BondpairPetService bondpairPetService;

    public void saveWithPet(BondpairDto bondpairDto){
        // Save family basic info, update bondpair sheet, execute insert
        this.save(bondpairDto);

        List<BondpairPet> bondpairPetes = bondpairDto.getBondpairPetes();
        bondpairPetes.stream().map((item) ->{
            item.setBondpairId(bondpairDto.getId());
            return item;
        }).collect(Collectors.toList());

        // Save family and animal relation info, insert in bondpair-pet sheet
        bondpairPetService.saveBatch(bondpairPetes);
    }

    /**
     * Delete a family, and also animal relations data
     * @param ids
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
     * 获取宠物家族详细信息，填充到页面上
     * @param id
     * @return
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
            bondpairDto.setBondpairPetes(dishes);

            return bondpairDto;
        }

        return null;
    }
}