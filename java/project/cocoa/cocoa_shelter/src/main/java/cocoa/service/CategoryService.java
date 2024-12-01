package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cocoa.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);

}
