package reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import reggie.dto.DishDto;
import reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    // Create new animal, and insert details, need to update two sheets(dish&dish-flavor)
    public void saveWithFlavor(DishDto dishDto);

    // Check dish details and flavor
    public DishDto getByIdWithFlavor(Long id);

    // Update dish info, and dish details
    public void updateWithFlavor(DishDto dishDto);
}
