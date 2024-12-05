package cocoa.controller;

import cocoa.common.R;
import cocoa.entity.User;
import cocoa.service.UserService;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private MockHttpSession session;


  @Autowired
  private MockMvc mockMvc;
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    session = new MockHttpSession();
  }

  @Test
  public void testSendMsgSuccess() {
    User user = new User();
    user.setPhone("1234567890");

    R<String> result = userController.sendMsg(user, session);
    Assertions.assertNotNull(result);
    Assertions.assertEquals("Verification code sent successfully, valid for 5 minutes.", result.getData());

  }

  @Test
  public void testLoginSuccess() {
    // Setup mock data
    String phone = "1234567890";
    String code = "1234";
    String sessionCode = "1234"; // The same code stored in session
    User user = new User();
    user.setPhone(phone);
    user.setId(1L);

    // Mocking UserService
    when(userService.getOne(Mockito.any())).thenReturn(user);

    // Setup session with the code
    session.setAttribute(phone, sessionCode);  // Storing code in session using phone as key

    // Call the login method
    R<String> result = userController.login(Map.of("phone", phone, "code", code), session);

    // Verify the result
    assertEquals(1, result.getCode());  // Verifying status code
    assertEquals(1L, session.getAttribute("user"));  // Verifying the user is set in session
  }
}