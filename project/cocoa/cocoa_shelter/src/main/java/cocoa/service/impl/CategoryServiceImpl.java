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

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements
    CategoryService {

    @Autowired
    private BondpairService bondpairService;
    @Autowired
    private PetService petService;
    /**
     * Delete category by id
     * @param id
     */
    @Override
    public void remove(Long id){
        // check by id
        LambdaQueryWrapper<Pet> petLambdaQueryWrapper = new LambdaQueryWrapper<>();
        petLambdaQueryWrapper.eq(Pet::getCategoryId, id);
        int countPet = (int) petService.count(petLambdaQueryWrapper);

        // Check if there is any petes under this category
        if (countPet > 0){
            // Pet under this category, throw error
            throw new CustomException("There are animals under this category, can not delete");
        }
        // Check if there is any pair under this category
        LambdaQueryWrapper<Bondpair> bondpairLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bondpairLambdaQueryWrapper.eq(Bondpair::getCategoryId, id);
        int countPair = (int) bondpairService.count(bondpairLambdaQueryWrapper);
        if (countPair > 0){
            // Pair under this category, throw error
            throw new CustomException("There are animal families under this category, can not delete");
        }

        // Delete
        super.removeById(id);
    }

}
