package cocoa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cocoa.dto.PetDto;
import cocoa.entity.Pet;
import cocoa.entity.PetDetail;
import cocoa.mapper.PetMapper;
import cocoa.service.PetDetailService;
import cocoa.service.PetService;
import java.util.List;

@Service
@Slf4j
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {

    @Autowired
    private PetDetailService petDetailService;

    /**
     * Create new animal and save details
     * @param petDto
     */
    @Transactional
    public void saveWithDetail(PetDto petDto){
        // Save basic info to sheet pet
        this.save(petDto);

        Long petId = petDto.getId();

        // Details
        List<PetDetail> details = petDto.getDetails();
        details = details.stream().map((item) ->{
            item.setPetId(petId);
            return item;
        }).collect(Collectors.toList());

        // Save details to pet-detail
        petDetailService.saveBatch(details);
    }

    /**
     * Check pet details and detail
     * @param id
     * @return
     */
    public PetDto getByIdWithDetail(Long id){
        // Check basic pet info, from pet sheet
        Pet pet = this.getById(id);

        PetDto petDto = new PetDto();
        BeanUtils.copyProperties(pet, petDto);

        // Check pet details, from pet-detail sheet
        LambdaQueryWrapper<PetDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetDetail::getPetId, pet.getId());
        List<PetDetail> details = petDetailService.list(queryWrapper);
        petDto.setDetails(details);
        return petDto;
    }

    @Override
    @Transactional
    public void updateWithDetail(PetDto petDto){
        // Update pet sheet
        this.updateById(petDto);

        // Clear current pet detail, delete in pet-detail sheet
        LambdaQueryWrapper<PetDetail> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PetDetail::getPetId, petDto.getId());

        petDetailService.remove(queryWrapper);

        // Add updated pet detail(inset in pet-detail sheet)
        List<PetDetail> details = petDto.getDetails();

        details = details.stream().map((item) ->{
            item.setPetId(petDto.getId());
            return item;
        }).collect(Collectors.toList());

        petDetailService.saveBatch(details);
    }
}