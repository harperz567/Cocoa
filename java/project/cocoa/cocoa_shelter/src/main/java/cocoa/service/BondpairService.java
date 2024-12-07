package cocoa.service;

import cocoa.dto.PetDto;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import cocoa.dto.BondpairDto;
import cocoa.entity.Bondpair;

/**
 * Service interface for managing Bondpair entities.
 * Extends the IService interface from MyBatis-Plus to provide common CRUD operations.
 * Includes custom methods for saving, deleting, and retrieving Bondpair data,
 * as well as managing BondpairPet relationships.
 */
public interface BondpairService extends IService<Bondpair> {

    /**
     * Adds a new family (Bondpair) and saves the related animal and animal family relationships.
     * This method will save the Bondpair entity and then persist the relationship between Bondpair
     * and its associated pets.
     *
     * @param bondpairDto The BondpairDto containing the Bondpair and its associated pets.
     */
    public void saveWithPet(BondpairDto bondpairDto);

    /**
     * Deletes a family (Bondpair) along with the related animal-family relationships.
     * This method first checks if the Bondpair can be deleted by validating its adoption status.
     * If valid, it deletes the Bondpair and the associated BondpairPet records.
     *
     * @param ids A list of Bondpair IDs to be deleted.
     */
    public void removeWithPet(List<Long> ids);

    /**
     * Retrieves the data of a specific Bondpair, including its associated animals (BondpairPets).
     * This method will return a BondpairDto containing the Bondpair and its associated pets.
     *
     * @param id The ID of the Bondpair to retrieve.
     * @return A BondpairDto containing the Bondpair data and its associated BondpairPets, or null if not found.
     */
    public BondpairDto getBondpairData(Long id);


}