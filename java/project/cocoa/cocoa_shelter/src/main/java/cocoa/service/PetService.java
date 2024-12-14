package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cocoa.dto.PetDto;
import cocoa.entity.Pet;

/**
 * Service interface for managing {@link cocoa.entity.Pet} entities.
 * Extends the MyBatis-Plus {@link com.baomidou.mybatisplus.extension.service.IService} interface
 * and provides additional methods for handling Pet entities and their associated details.
 */
public interface PetService extends IService<Pet> {
    /**
     * Create new animal, and insert details, need to update two sheets (pet and pet-detail).
     *
     * @param petDto the data transfer object containing pet and detail information
     */
    public void saveWithDetail(PetDto petDto);

    /**
     * Check pet details and detail.
     *
     * @param id the ID of the pet to retrieve
     * @return a {@link PetDto} containing the pet and its details
     */
    public PetDto getByIdWithDetail(Long id);

    /**
     * Update pet info, and pet details.
     *
     * @param petDto the data transfer object containing updated pet and detail information
     */
    public void updateWithDetail(PetDto petDto);
}