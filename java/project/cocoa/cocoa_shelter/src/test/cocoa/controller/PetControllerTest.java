package cocoa.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cocoa.common.R;
import cocoa.dto.PetDto;
import cocoa.entity.Category;
import cocoa.entity.Pet;
import cocoa.entity.PetDetail;
import cocoa.service.CategoryService;
import cocoa.service.PetDetailService;
import cocoa.service.PetService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


class PetControllerTest {

  @InjectMocks
  private PetController petController;
  private MockMvc mockMvc;
  @Mock
  private PetService petService;
  @Mock
  private PetDetailService petDetailService;
  @Mock

  private CategoryService categoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
  }

  @Test
  void save() {
    PetDto petDto = new PetDto();
    petDto.setName("Buddy");
    petDto.setCategoryId(1L);

    doNothing().when(petService).saveWithDetail(petDto);

    R<String> response = petController.save(petDto);
    assertEquals("Successfully added a pet!", response.getData());
    verify(petService, times(1)).saveWithDetail(petDto);
  }

  @Test
  void testPage() throws Exception {
    mockMvc.perform(get("/pet/page")
            .param("page", "1")
            .param("pageSize", "10")
            .param("name", "Buddy"))
        .andExpect(status().isOk())
        .andDo(print()); // Print request and response for debugging
  }




  @Test
  void getPetIDTest() {
    long petId = 1L;

    PetDto mockPetDto = new PetDto();
    mockPetDto.setId(petId);
    mockPetDto.setName("Buddy");
    mockPetDto.setCategoryId(1L);

    when(petService.getByIdWithDetail(petId)).thenReturn(mockPetDto);

    R<PetDto> response = petController.get(petId);
    PetDto result = response.getData();
    assertNotNull(result);
    assertEquals("Buddy", result.getName());
  }

  @Test
  void update() {
    PetDto petDto = new PetDto();
    petDto.setId(1L);
    petDto.setName("Buddy Updated");

    doNothing().when(petService).updateWithDetail(petDto);

    R<String> response = petController.update(petDto);
    assertEquals("Successfully updated a pet!", response.getData());
    verify(petService, times(1)).updateWithDetail(petDto);
  }

  @Test
  void list() {
    // 准备测试数据
    Pet pet = new Pet();
    pet.setCategoryId(1L); // 设置过滤条件

    Pet mockPet = new Pet();
    mockPet.setId(1L); // 设置ID以便关联
    mockPet.setName("Buddy");
    mockPet.setCategoryId(1L);
    mockPet.setStatus(1);
    mockPet.setSort(1);
    mockPet.setUpdateTime(LocalDateTime.now());

    Category mockCategory = new Category();
    mockCategory.setId(1L);
    mockCategory.setName("Dog");

    PetDetail mockDetail = new PetDetail();
    mockDetail.setPetId(1L);
  //  mockDetail.setDescription("Friendly dog");

    // 模拟服务行为
    when(petService.list(any(LambdaQueryWrapper.class))).thenReturn(List.of(mockPet));
    when(categoryService.getById(1L)).thenReturn(mockCategory);
    when(petDetailService.list(any(LambdaQueryWrapper.class))).thenReturn(List.of(mockDetail));

    // 调用测试方法
    R<List<PetDto>> response = petController.list(pet);

    // 验证返回结果
    List<PetDto> result = response.getData();
    assertNotNull(result); // 验证结果非空
    assertEquals(1, result.size()); // 验证返回列表的大小
    assertEquals("Buddy", result.get(0).getName()); // 验证返回的名字
    assertEquals("Dog", result.get(0).getCategoryName()); // 验证类别名称
  //  assertEquals("Friendly dog", result.get(0).getDetails().get(0).getDescription()); // 验证宠物详情
  }


  @Test
  void deleteTest() throws Exception {
    long petId = 1L;

    // Mock the petService behavior
    when(petService.removeById(petId)).thenReturn(true);

    // Perform DELETE request and verify the response
    mockMvc.perform(delete("/pet")
            .param("ids", String.valueOf(petId)))  // Pass the petId as a request parameter
        .andExpect(status().isOk())  // Verify the HTTP status is 200 OK
        .andExpect(jsonPath("$.data").value("Deleted a pet"));  // Verify the response message

    // Verify that petService.removeById(petId) was called once
    verify(petService, times(1)).removeById(petId);
  }

}
