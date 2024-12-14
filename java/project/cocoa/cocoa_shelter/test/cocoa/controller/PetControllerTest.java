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

/**
 * Unit test class for PetController.
 * Contains test cases to validate the functionality of CRUD operations and other features
 * provided by the PetController.
 */
class PetControllerTest {

  // Mock the PetController to test its behavior in isolation
  @InjectMocks
  private PetController petController;

  // MockMvc is used to simulate HTTP requests for controller endpoints
  private MockMvc mockMvc;

  // Mocked service dependencies
  @Mock
  private PetService petService;
  @Mock
  private PetDetailService petDetailService;
  @Mock
  private CategoryService categoryService;

  /**
   * Initializes mock objects and sets up the test environment.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
  }

  /**
   * Tests the save method to verify adding a new pet.
   */
  @Test
  void save() {
    PetDto petDto = new PetDto();
    petDto.setName("Buddy");
    petDto.setCategoryId(1L);

    // Mock the saveWithDetail method of PetService
    doNothing().when(petService).saveWithDetail(petDto);

    // Call the save method of PetController
    R<String> response = petController.save(petDto);

    // Validate the response and verify service interaction
    assertEquals("Successfully added a pet!", response.getData());
    verify(petService, times(1)).saveWithDetail(petDto);
  }

  /**
   * Tests the pagination endpoint for pets.
   */
  @Test
  void testPage() throws Exception {
    mockMvc.perform(get("/pet/page")
            .param("page", "1")
            .param("pageSize", "10")
            .param("name", "Buddy"))
        .andExpect(status().isOk())
        .andDo(print()); // Print request and response for debugging
  }

  /**
   * Tests retrieving a pet by ID.
   */
  @Test
  void getPetIDTest() {
    long petId = 1L;

    // Mock data for a pet
    PetDto mockPetDto = new PetDto();
    mockPetDto.setId(petId);
    mockPetDto.setName("Buddy");
    mockPetDto.setCategoryId(1L);

    // Mock the behavior of getByIdWithDetail
    when(petService.getByIdWithDetail(petId)).thenReturn(mockPetDto);

    // Call the get method of PetController
    R<PetDto> response = petController.get(petId);
    PetDto result = response.getData();

    // Validate the response
    assertNotNull(result);
    assertEquals("Buddy", result.getName());
  }

  /**
   * Tests updating a pet's details.
   */
  @Test
  void update() {
    PetDto petDto = new PetDto();
    petDto.setId(1L);
    petDto.setName("Buddy Updated");

    // Mock the updateWithDetail method of PetService
    doNothing().when(petService).updateWithDetail(petDto);

    // Call the update method of PetController
    R<String> response = petController.update(petDto);

    // Validate the response and verify service interaction
    assertEquals("Successfully updated a pet!", response.getData());
    verify(petService, times(1)).updateWithDetail(petDto);
  }

  /**
   * Tests listing pets with additional details such as category and description.
   */
  @Test
  void list() {
    Pet pet = new Pet();
    pet.setCategoryId(1L);

    // Mock data for pets, categories, and details
    Pet mockPet = new Pet();
    mockPet.setId(1L);
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

    // Mock service responses
    when(petService.list(any(LambdaQueryWrapper.class))).thenReturn(List.of(mockPet));
    when(categoryService.getById(1L)).thenReturn(mockCategory);
    when(petDetailService.list(any(LambdaQueryWrapper.class))).thenReturn(List.of(mockDetail));

    // Call the list method of PetController
    R<List<PetDto>> response = petController.list(pet);
    List<PetDto> result = response.getData();

    // Validate the response
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Buddy", result.get(0).getName());
    assertEquals("Dog", result.get(0).getCategoryName());
  }

  /**
   * Tests deleting a pet by ID.
   */
  @Test
  void deleteTest() throws Exception {
    long petId = 1L;

    // Mock the behavior of removeById in PetService
    when(petService.removeById(petId)).thenReturn(true);

    // Perform DELETE request and validate response
    mockMvc.perform(delete("/pet")
            .param("ids", String.valueOf(petId)))  // Pass the petId as a request parameter
        .andExpect(status().isOk())  // Verify the HTTP status is 200 OK
        .andExpect(jsonPath("$.data").value("Deleted a pet"));  // Verify the response message

    // Verify service method interaction
    verify(petService, times(1)).removeById(petId);
  }
}
