package cocoa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cocoa.entity.Category;
import cocoa.entity.Pet;
import cocoa.entity.Bondpair;
import cocoa.mapper.CategoryMapper;
import cocoa.service.CategoryService;
import cocoa.service.PetService;
import cocoa.service.BondpairService;
import cocoa.common.CustomException;

/**
 * Service implementation for handling operations related to Category.
 * Extends ServiceImpl and implements CategoryService.
 * Provides business logic for removing a Category and checking related entities.
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private BondpairService bondpairService;

    @Autowired
    private PetService petService;

    /**
     * Removes a category by its ID.
     * This method performs checks to ensure that no Pets or Bondpairs exist under the specified category
     * before allowing the deletion. If any related Pets or Bondpairs are found, an exception is thrown.
     *
     * @param id The ID of the category to be removed.
     * @throws CustomException if there are related Pets or Bondpairs under the category,
     *                         preventing the deletion.
     */
    @Override
    public void remove(Long id){
        // Check if there are any pets under this category
        LambdaQueryWrapper<Pet> petLambdaQueryWrapper = new LambdaQueryWrapper<>();
        petLambdaQueryWrapper.eq(Pet::getCategoryId, id);
        int countPet = (int) petService.count(petLambdaQueryWrapper);

        // If pets exist under this category, throw an exception
        if (countPet > 0){
            throw new CustomException("There are animals under this category, can not delete");
        }

        // Check if there are any bondpairs under this category
        LambdaQueryWrapper<Bondpair> bondpairLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bondpairLambdaQueryWrapper.eq(Bondpair::getCategoryId, id);
        int countPair = (int) bondpairService.count(bondpairLambdaQueryWrapper);

        // If bondpairs exist under this category, throw an exception
        if (countPair > 0){
            throw new CustomException("There are animal families under this category, can not delete");
        }

        // Proceed to delete the category if no related entities are found
        super.removeById(id);
    }

}
