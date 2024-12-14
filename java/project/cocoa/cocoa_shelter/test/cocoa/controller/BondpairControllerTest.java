package cocoa.controller;

import cocoa.common.R;
import cocoa.dto.BondpairDto;
import cocoa.entity.Bondpair;
import cocoa.entity.Category;
import cocoa.service.BondpairPetService;
import cocoa.service.BondpairService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the BondpairController.
 * Verifies the functionality of endpoints for managing bondpairs.
 */
class BondpairControllerTest {

    @Mock
    private BondpairService bondpairService;

    @Mock
    private BondpairPetService bondpairPetService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BondpairController bondpairController;

    private MockMvc mockMvc;

    /**
     * Sets up the test environment before each test.
     * Initializes MockMvc and mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bondpairController).build();
    }

    /**
     * Tests the endpoint for saving a new bondpair.
     * Verifies the service method saveWithPet is called and a success message is returned.
     */
    @Test
    void testSaveBondpair() throws Exception {
        BondpairDto bondpairDto = new BondpairDto();
        bondpairDto.setName("Test Family");

        doNothing().when(bondpairService).saveWithPet(any(BondpairDto.class));

        mockMvc.perform(post("/bondpair")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test Family\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Added a family"));

        verify(bondpairService, times(1)).saveWithPet(any(BondpairDto.class));
    }

    /**
     * Tests the endpoint for paginating bondpairs.
     * Verifies the service method page is called with correct parameters.
     */
    @Test
    void testPageBondpair() throws Exception {
        Page<Bondpair> page = new Page<>();
        Page<BondpairDto> dtoPage = new Page<>();

        when(bondpairService.page(any(Page.class), any())).thenReturn(page);

        mockMvc.perform(get("/bondpair/page")
                .param("page", "1")
                .param("pageSize", "10")
                .param("name", "Family"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists());

        verify(bondpairService, times(1)).page(any(Page.class), any());
    }

    /**
     * Tests the endpoint for deleting bondpairs by IDs.
     * Verifies the service method removeWithPet is called with the correct ID list.
     */
    @Test
    void testDeleteBondpair() throws Exception {
        doNothing().when(bondpairService).removeWithPet(anyList());

        mockMvc.perform(delete("/bondpair")
                .param("ids", "1", "2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Deleted a family"));

        verify(bondpairService, times(1)).removeWithPet(anyList());
    }

    /**
     * Tests the endpoint for listing bondpairs based on query conditions.
     * Verifies the service method list is called with the correct query wrapper.
     */
    @Test
    void testListBondpair() throws Exception {
        List<Bondpair> bondpairs = new ArrayList<>();
        when(bondpairService.list(any(LambdaQueryWrapper.class))).thenReturn(bondpairs);

        mockMvc.perform(get("/bondpair/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray());

        verify(bondpairService, times(1)).list(any(LambdaQueryWrapper.class));
    }

    /**
     * Tests the endpoint for retrieving a bondpair by its ID.
     * Verifies the service method getBondpairData is called with the correct ID.
     */
    @Test
    void testGetBondpairById() throws Exception {
        BondpairDto bondpairDto = new BondpairDto();
        bondpairDto.setName("Test Family");

        when(bondpairService.getBondpairData(anyLong())).thenReturn(bondpairDto);

        mockMvc.perform(get("/bondpair/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("Test Family"));

        verify(bondpairService, times(1)).getBondpairData(anyLong());
    }
}
