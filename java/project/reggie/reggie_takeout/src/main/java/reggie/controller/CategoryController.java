package reggie.controller;

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
import reggie.common.R;
import reggie.entity.Category;
import reggie.service.CategoryService;

/**
 * Category management, has 5 methods: add, browse, delete, update, query
 */
@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Add new category
     * @param category
     * @return returns a string(a notification message)
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category: {}", category);
        categoryService.save(category);
        return R.success("Added a new category");
    }

    /**
     * Browse all categories with pagination
     * @param page
     * @param pageSize
     * @return
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
     * Delete a category
     * @param id of the deleted category
     * @return returns a string(a notification message)
     */
    @DeleteMapping
    public R<String> delete(@RequestParam String id){
        log.info("Delete category, category id: {}", id);
        categoryService.remove(Long.parseLong(id));
        return R.success("Deleted a category");
    }

    /**
     * Update a category
     * @param category
     * @return returns a string(a notification message)
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("Updated info: {}", category);
        categoryService.updateById(category);
        return R.success("Updated successfully!");
    }

    /**
     * Query all animals/pairs based on category
     * @param category
     * @return return a list of animals/pairs under a certain category
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
