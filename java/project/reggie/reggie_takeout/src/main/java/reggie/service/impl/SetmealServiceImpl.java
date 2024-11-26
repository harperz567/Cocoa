package reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reggie.common.CustomException;
import reggie.dto.SetmealDto;
import reggie.entity.Setmeal;
import reggie.entity.SetmealDish;
import reggie.mapper.SetmealMapper;
import reggie.service.SetmealDishService;
import reggie.service.SetmealService;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements
    SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    public void saveWithDish(SetmealDto setmealDto){
        // Save family basic info, update setmeal sheet, execute insert
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) ->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        // Save family and animal relation info, insert in setmeal-dish sheet
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * Delete a family, and also animal relations data
     * @param ids
     */
    public void removeWithDish(List<Long> ids){
        // SQL: Select count(*) from setmeal where id in (1, 2, 3) and status = 1
        // Check adoption status to see if we can delete that family
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        long count = this.count(queryWrapper);
        if (count > 0) {
            // If we can not delete, throw an error
            throw new CustomException("Family are not adopted, can not delete");
        }

        // If we can delete, first we delete the data in family sheet
        this.removeByIds(ids);

        // SQL: Delete from setmeal-dish where setmeal id in (1, 2, 3)
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        // Delete relations sheet(setmeal-dish)
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
