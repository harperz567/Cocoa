package cocoa.controller;

import cocoa.common.R;
import cocoa.controller.EmployeeController;
import cocoa.entity.Employee;
import cocoa.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the EmployeeController class.
 * Tests the login and logout functionalities to ensure proper behavior.
 */
class EmployeeControllerTest {

  @InjectMocks
  private EmployeeController employeeController;

  @Mock
  private EmployeeService employeeService;

  private MockHttpServletRequest request;

  /**
   * Initializes the mocks and sets up the HTTP request object before each test.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    request = new MockHttpServletRequest();
  }


  /**
   * Tests the successful login scenario.
   * Verifies that the session is updated and the correct response is returned.
   */
  @Test
  void testLoginSuccess() {
    Employee mockEmployee = new Employee();
    mockEmployee.setUsername("testUser");
    mockEmployee.setPassword("5d41402abc4b2a76b9719d911017c592"); // "hello" in MD5
    mockEmployee.setStatus(1);

    when(employeeService.getOne(any())).thenReturn(mockEmployee);

    Employee loginRequest = new Employee();
    loginRequest.setUsername("testUser");
    loginRequest.setPassword("hello");

    R<Employee> response = employeeController.login(request, loginRequest);

    assertEquals(1, response.getCode());
  }

  /**
   * Tests the login failure scenario when the credentials are invalid.
   * Ensures that the correct error message and status code are returned.
   */
  @Test
  void testLoginFailureInvalidCredentials() {
    when(employeeService.getOne(any())).thenReturn(null);

    Employee loginRequest = new Employee();
    loginRequest.setUsername("invalidUser");
    loginRequest.setPassword("invalidPassword");

    R<Employee> response = employeeController.login(request, loginRequest);

    assertEquals(0, response.getCode()); // Assuming 500 indicates failure
    assertEquals("Login Failed!", response.getMsg());
  }

  /**
   * Tests the login failure scenario when the employee account is inactive.
   * Verifies that the correct error message and status code are returned.
   */
  @Test
  void testLoginFailureInactiveAccount() {
    Employee mockEmployee = new Employee();
    mockEmployee.setUsername("testUser");
    mockEmployee.setPassword("5d41402abc4b2a76b9719d911017c592"); // "hello" in MD5
    mockEmployee.setStatus(0);

    when(employeeService.getOne(any())).thenReturn(mockEmployee);

    Employee loginRequest = new Employee();
    loginRequest.setUsername("testUser");
    loginRequest.setPassword("hello");

    R<Employee> response = employeeController.login(request, loginRequest);

    assertEquals(0, response.getCode());
    assertEquals("You Have No Access!", response.getMsg());
  }

  /**
   * Tests the logout functionality.
   * Ensures that the session is cleared and the correct success message is returned.
   */
  @Test
  void testLogout() {
    request.getSession().setAttribute("employee", 1L);

    R<String> response = employeeController.logout(request);

    assertEquals(1, response.getCode());
    assertNull(request.getSession().getAttribute("employee"));
  }
}