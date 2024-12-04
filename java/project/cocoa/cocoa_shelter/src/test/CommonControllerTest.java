package cocoa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommonControllerTest {

  @InjectMocks
  private CommonController commonController;

  @Autowired
  private MockMvc mockMvc;

  @Value("${cocoa.path}")
  private String basePath;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    commonController.setBasePath(basePath); // 模拟basePath注入
  }

  @Test
  public void testUpLoadFile() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "testfile.txt",
        "text/plain",
        "Some content".getBytes()
    );

    mockMvc.perform(multipart("/common/upload")
            .file(file))
        .andExpect(status().isOk())  // Ensure status is 200 OK
        .andExpect(jsonPath("$.code").value(1))  // Expecting the response code to be 1
        .andExpect(jsonPath("$.data").exists());  // Ensure that the data field exists (it can be any value)
  }

  @Test
  void testFileDownload() throws Exception {
    // Create a temporary file for download.
    String fileName = "downloadTest.jpeg";
    Files.write(Paths.get(basePath, fileName), "Test content".getBytes());

    // Execute the download request
    mockMvc.perform(MockMvcRequestBuilders.get("/common/download")
            .param("name", fileName))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("image/jpeg"));

    // Clean the test files.
    Files.deleteIfExists(Paths.get(basePath, fileName));
  }
}
