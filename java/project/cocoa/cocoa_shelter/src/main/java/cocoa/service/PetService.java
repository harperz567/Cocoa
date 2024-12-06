package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cocoa.dto.PetDto;
import cocoa.entity.Pet;

public interface PetService extends IService<Pet> {
    // Create new animal, and insert details, need to update two sheets(pet&pet-detail)
    public void saveWithDetail(PetDto petDto);

    // Check pet details and detail
    public PetDto getByIdWithDetail(Long id);

    // Update pet info, and pet details
    public void updateWithDetail(PetDto petDto);
}