package reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reggie.common.R;
import reggie.dto.PetDto;
import reggie.entity.Category;
import reggie.entity.PetDetail;
import reggie.entity.Pet;
import reggie.service.CategoryService;
import reggie.service.PetDetailService;
import reggie.service.PetService;

/**
 * Pet management
 */
@Slf4j
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private PetDetailService petDetailService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Create new pet
     * @param petDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody PetDto petDto){
        log.info(petDto.toString());
        petService.saveWithDetail(petDto);
        return R.success("Successfully added a pet!");
    }

    /**
     * Pet info check by page
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        // Set up a pagination builder object
        Page<Pet> pageInfo = new Page<>(page, pageSize);
        Page<PetDto> petDtoPage = new Page<>();
        // Condition tool
        LambdaQueryWrapper<Pet> queryWrapper = new LambdaQueryWrapper<>();
        // Add condition
        queryWrapper.like(name != null, Pet::getName, name);
        // Add sorting
        queryWrapper.orderByDesc(Pet::getUpdateTime);

        // Execute checking by page
        petService.page(pageInfo, queryWrapper);

        // Copy object
        BeanUtils.copyProperties(pageInfo, petDtoPage, "records");
        List<Pet> records = pageInfo.getRecords();
        List<PetDto> list = records.stream().map((item) ->{
            PetDto petDto = new PetDto();
            BeanUtils.copyProperties(item, petDto);
            Long categoryId = item.getCategoryId();
            // Check category through id
            Category category = categoryService.getById(categoryId);

            if (category != null){
                String categoryName = category.getName();
                petDto.setCategoryName(categoryName);
            }
            return petDto;
        }).collect(Collectors.toList());

        petDtoPage.setRecords(list);
        return R.success(petDtoPage);
    }

    /**
     * Check pet details according to id
     * @param id
     * @return
     */
//    @GetMapping("/{id}")
//    public R<PetDto> get(@PathVariable Long id){
//        PetDto petDto = petService.getByIdWithDetail(id);
//        return R.success(petDto);
//    }



    @CachePut(value = "userCache",key="#petDto.id")
    @GetMapping("/{id}")
    public R<PetDto> get(@PathVariable Long id){
        //因为是直接查Dto数据嘛，用现成的肯定不行了，在Service层自己写，这是个多表联查的过程
        PetDto petDto=petService.getByIdWithDetail(id);
        return R.success(petDto);
    }

    /**
     * Update a pet
     * @param petDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody PetDto petDto){
        log.info(petDto.toString());
        petService.updateWithDetail(petDto);
        return R.success("Successfully updated a pet!");
    }

    /**
     * Get pet data through query condition
     * @param pet
     * @return
     */
    @GetMapping("/list")
    public R<List<PetDto>> list(Pet pet){
        // Create query condition
        LambdaQueryWrapper<Pet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(pet.getCategoryId() != null, Pet::getCategoryId, pet.getCategoryId());

        // Add condition(Not adopted, 1)
        queryWrapper.eq(Pet::getStatus, 1);

        // Add sorting
        queryWrapper.orderByAsc(Pet::getSort).orderByDesc(Pet::getUpdateTime);

        List<Pet> list = petService.list(queryWrapper);

        List<PetDto> petDtoList = list.stream().map((item) ->{
            PetDto petDto = new PetDto();
            BeanUtils.copyProperties(item, petDto);
            Long categoryId = item.getCategoryId();
            // Check category through id
            Category category = categoryService.getById(categoryId);

            if (category != null){
                String categoryName = category.getName();
                petDto.setCategoryName(categoryName);
            }
            // Current pet family id
            Long petId = item.getId();
            LambdaQueryWrapper<PetDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(PetDetail::getPetId, petId);
            // Select * from pet-details where id = ?
            List<PetDetail> petDetailList = petDetailService.list(lambdaQueryWrapper);
            petDto.setDetails(petDetailList);

            return petDto;
        }).collect(Collectors.toList());

        return R.success(petDtoList);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam Long ids){
        log.info("Delete a pet, pet id: {}", ids);
        petService.removeById(ids);
        return R.success("Deleted a pet");
    }
}
