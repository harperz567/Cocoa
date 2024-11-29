package reggie.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reggie.common.R;
import reggie.dto.BondpairDto;
import reggie.entity.Category;
import reggie.entity.Bondpair;
import reggie.service.CategoryService;
import reggie.service.BondpairPetService;
import reggie.service.BondpairService;

/**
 * Animal family management
 */
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
     * Add an animal family
     * @param bondpairDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody BondpairDto bondpairDto){
        log.info("Family info: {}", bondpairDto);
        bondpairService.saveWithPet(bondpairDto);
        return R.success("Added a family");
    }

    /**
     * Animal family query and check by page
     * @param page
     * @param pageSize
     * @param name
     * @return
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
     * Delete a family
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids: {}", ids);

        bondpairService.removeWithPet(ids);
        return R.success("Deleted a family");
    }

    /**
     * Display animal family data according to query condition
     * @param bondpair
     * @return
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

    @GetMapping("/{id}")
    public R<BondpairDto> getSetmal(@PathVariable("id") Long id){
        log.info("获取套餐Id"+id);
        BondpairDto setmealDto= bondpairService.getBondpairData(id);
        return R.success(setmealDto);
    }
}
