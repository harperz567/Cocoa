/**
 * Controller for managing animal families (Bondpairs).
 * Provides APIs for creating, querying, updating, and deleting bondpairs.
 */
package cocoa.controller;

import cocoa.dto.PetDto;
import cocoa.service.PetDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cocoa.common.R;
import cocoa.dto.BondpairDto;
import cocoa.entity.Category;
import cocoa.entity.Bondpair;
import cocoa.service.CategoryService;
import cocoa.service.BondpairPetService;
import cocoa.service.BondpairService;


@RestController
@RequestMapping("/bondpair")
@Slf4j
public class BondpairController {
    @Autowired
    private BondpairService bondpairService;
    @Autowired
    private BondpairPetService bondpairPetService;
    @Autowired
    private CategoryService categoryService;

    /**
     * Adds a new animal family (bondpair) along with its associated pets.
     *
     * @param bondpairDto the data transfer object containing bondpair details
     * @return a success message indicating the bondpair was added
     */
    @PostMapping
    public R<String> save(@RequestBody BondpairDto bondpairDto){
        log.info("Family info: {}", bondpairDto);
        bondpairService.saveWithPet(bondpairDto);
        return R.success("Added a family");
    }

    /**
     * Queries and paginates animal families based on name and other conditions.
     *
     * @param page the current page number
     * @param pageSize the number of records per page
     * @param name the name filter for querying bondpairs (optional)
     * @return a paginated result of bondpairs
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        // Query by page tool
        Page<Bondpair> pageInfo = new Page<>(page, pageSize);
        Page<BondpairDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Bondpair> queryWrapper = new LambdaQueryWrapper<>();

        // Add query condition
        queryWrapper.like(name != null, Bondpair::getName, name);
        // Add sorting
        queryWrapper.orderByDesc(Bondpair::getUpdateTime);

        bondpairService.page(pageInfo, queryWrapper);

        // Copy object
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Bondpair> records = pageInfo.getRecords();

        List<BondpairDto> list = records.stream().map((item) ->{
            BondpairDto bondpairDto = new BondpairDto();
            // Copy object
            BeanUtils.copyProperties(item, bondpairDto);
            // Category id
            Long categoryId = item.getCategoryId();
            // Query category objects through category id
            Category category = categoryService.getById(categoryId);
            if (category != null){
                // category name
                String categoryName = category.getName();
                bondpairDto.setCategoryName(categoryName);
            }
            return bondpairDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * Deletes multiple animal families by their IDs.
     *
     * @param ids the list of bondpair IDs to delete
     * @return a success message indicating the bondpairs were deleted
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids: {}", ids);

        bondpairService.removeWithPet(ids);
        return R.success("Deleted a family");
    }

    /**
     * Queries animal families based on specific conditions.
     *
     * @param bondpair an object containing query conditions (e.g., category ID or status)
     * @return a list of bondpairs matching the query conditions
     */
    @GetMapping("/list")
    public R<List<Bondpair>> list(Bondpair bondpair){
        LambdaQueryWrapper<Bondpair> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(bondpair.getCategoryId() != null, Bondpair::getCategoryId, bondpair.getCategoryId());
        queryWrapper.eq(bondpair.getStatus() != null, Bondpair::getStatus, bondpair.getStatus());
        queryWrapper.orderByDesc(Bondpair::getUpdateTime);

        List<Bondpair> list = bondpairService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * Update a pair
     * @param bondpair
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Bondpair bondpair){
        log.info("Updated info: {}", bondpair);
        bondpairService.updateById(bondpair);
        return R.success("Updated successfully!");
    }

    /**
     * Retrieves detailed information about a specific animal family by its ID.
     *
     * @param id the ID of the bondpair to retrieve
     * @return a data transfer object containing bondpair details
     */
    @GetMapping("/{id}")
    public R<BondpairDto> getBondpair(@PathVariable("id") Long id){
        log.info("Get bond pair Id"+id);
        BondpairDto bondpairDto= bondpairService.getBondpairData(id);
        return R.success(bondpairDto);
    }
}