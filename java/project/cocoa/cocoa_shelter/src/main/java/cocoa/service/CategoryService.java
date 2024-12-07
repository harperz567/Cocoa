package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cocoa.entity.Category;

/**
 * Service interface for managing Category entities.
 * Extends IService from MyBatis-Plus for common CRUD operations.
 * Provides an additional method for removing a Category by its ID with specific business logic.
 */
public interface CategoryService extends IService<Category> {

    /**
     * Removes a Category by its ID.
     * This method performs business checks to ensure no related entities
     * (such as Pets or Bondpairs) exist under the category before allowing
     * the deletion.
     * @param id The ID of the Category to be removed.
     */
    public void remove(Long id);

}
