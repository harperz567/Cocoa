package reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reggie.entity.Category;
import reggie.entity.Dish;
import reggie.entity.Setmeal;
import reggie.mapper.CategoryMapper;
import reggie.service.CategoryService;
import reggie.service.DishService;
import reggie.service.SetmealService;
import reggie.common.CustomException;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements
    CategoryService {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;
    /**
     * Delete category by id
     * @param id
     */
    @Override
    public void remove(Long id){
        // check by id
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int countDish = (int) dishService.count(dishLambdaQueryWrapper);

        // Check if there is any dishes under this category
        if (countDish > 0){
            // Dish under this category, throw error
            throw new CustomException("There are animals under this category, can not delete");
        }
        // Check if there is any meal under this category
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int countMeal = (int) setmealService.count(setmealLambdaQueryWrapper);
        if (countMeal > 0){
            // Meal under this category, throw error
            throw new CustomException("There are animal families under this category, can not delete");
        }

        // Delete
        super.removeById(id);
    }

}
