/**
 * Controller for managing categories.
 * Provides APIs for creating, updating, deleting, and querying categories.
 */
package cocoa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cocoa.common.R;
import cocoa.entity.Category;
import cocoa.service.CategoryService;


@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Adds a new category.
     *
     * @param category the category object to be added
     * @return a success message indicating the category was added
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category: {}", category);
        categoryService.save(category);
        return R.success("Added a new category");
    }

    /**
     * Paginates through all categories.
     *
     * @param page the current page number
     * @param pageSize the number of records per page
     * @return a paginated result of categories
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        // Create page tool
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //Condition tool
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // Add condition, base on sort
        queryWrapper.orderByAsc(Category:: getSort);

        // Query by page
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);

    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to be deleted
     * @return a success message indicating the category was deleted
     */
    @DeleteMapping
    public R<String> delete(@RequestParam String id){
        log.info("Delete category, category id: {}", id);
        categoryService.remove(Long.parseLong(id));
        return R.success("Deleted a category");
    }

    /**
     * Updates an existing category.
     *
     * @param category the updated category object
     * @return a success message indicating the category was updated
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("Updated info: {}", category);
        categoryService.updateById(category);
        return R.success("Updated successfully!");
    }

    /**
     * Queries all animals/pairs under a specific category.
     *
     * @param category an object containing query conditions (e.g., type)
     * @return a list of categories matching the query conditions
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        // Create condition tool
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // Add condition
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        // Add sorting
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
