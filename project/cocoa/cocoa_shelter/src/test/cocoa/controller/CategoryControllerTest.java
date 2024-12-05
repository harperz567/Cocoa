package cocoa.controller;

import cocoa.common.R;
import cocoa.entity.Category;
import cocoa.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the CategoryController.
 * Verifies the functionality of endpoints for managing categories.
 */
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    /**
     * Sets up the test environment before each test.
     * Initializes MockMvc and mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    /**
     * Tests the endpoint for saving a new category.
     * Verifies the service method save is called and a success message is returned.
     */
    @Test
    void testSaveCategory() throws Exception {
        Category category = new Category();
        category.setName("Test Category");

        // 模拟 save 方法
        when(categoryService.save(any(Category.class))).thenReturn(true);

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test Category\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Added a new category"));

        verify(categoryService, times(1)).save(any(Category.class));
    }

    /**
     * Tests the endpoint for paginating categories.
     * Verifies the service method page is called with correct parameters.
     */
    @Test
    void testPageCategories() throws Exception {
        Page<Category> page = new Page<>();
        when(categoryService.page(any(Page.class), any())).thenReturn(page);

        mockMvc.perform(get("/category/page")
                .param("page", "1")
                .param("pageSize", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isNotEmpty());

        verify(categoryService, times(1)).page(any(Page.class), any());
    }

    /**
     * Tests the endpoint for deleting a category by its ID.
     * Verifies the service method remove is called with the correct ID.
     */
    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).remove(anyLong());

        mockMvc.perform(delete("/category")
                .param("id", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Deleted a category"));

        verify(categoryService, times(1)).remove(1L);
    }

    /**
     * Tests the endpoint for updating a category.
     * Verifies the service method updateById is called with the correct category object.
     */
    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setName("Updated Category");

        // 模拟 updateById 方法调用
        when(categoryService.updateById(any(Category.class))).thenReturn(true);

        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Category\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Updated successfully!"));

        verify(categoryService, times(1)).updateById(any(Category.class));
    }

    /**
     * Tests the endpoint for listing categories based on query conditions.
     * Verifies the service method list is called with the correct query wrapper.
     */
    @Test
    void testListCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        when(categoryService.list(any(LambdaQueryWrapper.class))).thenReturn(categories);

        mockMvc.perform(get("/category/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray());

        verify(categoryService, times(1)).list(any(LambdaQueryWrapper.class));
    }
}
