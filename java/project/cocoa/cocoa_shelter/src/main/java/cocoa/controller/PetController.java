package cocoa.controller;

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
import cocoa.common.R;
import cocoa.dto.PetDto;
import cocoa.entity.Category;
import cocoa.entity.PetDetail;
import cocoa.entity.Pet;
import cocoa.service.CategoryService;
import cocoa.service.PetDetailService;
import cocoa.service.PetService;

/**
 * PetController is a REST controller for managing pet entities.
 * It provides endpoints for creating, updating, deleting, and querying pet information.
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
     * Creates a new pet.
     *
     * @param petDto the data transfer object containing pet details
     * @return a response indicating success or failure
     */
    @PostMapping
    public R<String> save(@RequestBody PetDto petDto) {
        log.info(petDto.toString());
        petService.saveWithDetail(petDto);
        return R.success("Successfully added a pet!");
    }

    /**
     * Retrieves a paginated list of pets based on the given parameters.
     *
     * @param page     the page number
     * @param pageSize the number of items per page
     * @param name     an optional name to filter pets by
     * @return a paginated list of pets wrapped in a response object
     */
    @GetMapping("/page")
    public R<Page> page(@RequestParam int page,
        @RequestParam int pageSize,
        @RequestParam(required = false) String name) {
        Page<Pet> pageInfo = new Page<>(page, pageSize);
        Page<PetDto> petDtoPage = new Page<>();
        LambdaQueryWrapper<Pet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Pet::getName, name);
        queryWrapper.orderByDesc(Pet::getUpdateTime);
        petService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, petDtoPage, "records");
        List<Pet> records = pageInfo.getRecords();
        List<PetDto> list = records.stream().map(item -> {
            PetDto petDto = new PetDto();
            BeanUtils.copyProperties(item, petDto);
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                petDto.setCategoryName(category.getName());
            }
            return petDto;
        }).collect(Collectors.toList());
        petDtoPage.setRecords(list);
        return R.success(petDtoPage);
    }

    /**
     * Retrieves detailed information of a pet by its ID.
     *
     * @param id the ID of the pet
     * @return the detailed information of the pet wrapped in a response object
     */
    @CachePut(value = "userCache", key = "#petDto.id")
    @GetMapping("/{id}")
    public R<PetDto> get(@PathVariable Long id) {
        PetDto petDto = petService.getByIdWithDetail(id);
        return R.success(petDto);
    }

    /**
     * Updates an existing pet.
     *
     * @param petDto the data transfer object containing updated pet details
     * @return a response indicating success or failure
     */
    @PutMapping
    public R<String> update(@RequestBody PetDto petDto) {
        log.info(petDto.toString());
        petService.updateWithDetail(petDto);
        return R.success("Successfully updated a pet!");
    }

    /**
     * Retrieves a list of pets matching the given query conditions.
     *
     * @param pet the query conditions
     * @return a list of pets matching the conditions wrapped in a response object
     */
    @GetMapping("/list")
    public R<List<PetDto>> list(Pet pet) {
        LambdaQueryWrapper<Pet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(pet.getCategoryId() != null, Pet::getCategoryId, pet.getCategoryId());
        queryWrapper.eq(Pet::getStatus, 1);
        queryWrapper.orderByAsc(Pet::getSort).orderByDesc(Pet::getUpdateTime);
        List<Pet> list = petService.list(queryWrapper);
        List<PetDto> petDtoList = list.stream().map(item -> {
            PetDto petDto = new PetDto();
            BeanUtils.copyProperties(item, petDto);
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                petDto.setCategoryName(category.getName());
            }
            LambdaQueryWrapper<PetDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(PetDetail::getPetId, item.getId());
            List<PetDetail> petDetailList = petDetailService.list(lambdaQueryWrapper);
            petDto.setDetails(petDetailList);
            return petDto;
        }).collect(Collectors.toList());
        return R.success(petDtoList);
    }

    /**
     * Deletes a pet by its ID.
     *
     * @param ids the ID of the pet to delete
     * @return a response indicating success or failure
     */
    @DeleteMapping
    public R<String> delete(@RequestParam Long ids) {
        log.info("Delete a pet, pet id: {}", ids);
        petService.removeById(ids);
        return R.success("Deleted a pet");
    }

    /**
     * Updates the status of a pet to adopted.
     *
     * @param ids the ID of the pet
     * @return a response indicating success or failure
     */
    @PostMapping("/status/0")
    public R<String> updateToAdopted(Long ids) {
        Pet pet = petService.getById(ids);
        pet.setStatus(0);
        LambdaQueryWrapper<Pet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pet::getId, ids);
        petService.update(pet, lambdaQueryWrapper);
        return R.success("Updated!");
    }

    /**
     * Updates the status of a pet to up for adoption.
     *
     * @param ids the ID of the pet
     * @return a response indicating success or failure
     */
    @PostMapping("/status/1")
    public R<String> updateToNotAdopted(Long ids) {
        Pet pet = petService.getById(ids);
        pet.setStatus(1);
        LambdaQueryWrapper<Pet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pet::getId, ids);
        petService.update(pet, lambdaQueryWrapper);
        return R.success("Updated");
    }
}
