package reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import reggie.dto.SetmealDto;
import reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {

    /**
     * Add new family, save animal and animal family relations
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * Delete a family, and also animal relations data
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
