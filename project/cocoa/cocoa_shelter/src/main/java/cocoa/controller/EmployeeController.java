package cocoa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cocoa.common.R;
import cocoa.service.EmployeeService;
import cocoa.entity.Employee;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * The login method
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        // 1.Use md5 to encrypt the password submitted from the page
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2.Query database with username
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        /**
         * LambdaQueryWrapper is a query form from MyBatis-Plus, we use this
         * so that we do not have to type sql queries
         * .eq(...) is an equal method (WHERE ... = ...)
         * Employee::getUsername is a method reference, informing MyBatis-Plus
         * that we want to query the username field
         */

        // 3.If there is no such user, return fail
        if (emp == null){
            return R.error("Login Failed!");
        }

        // 4.Compare password, if not match, return fail
        if (!emp.getPassword().equals(password)){
            return R.error("Login Failed!");
        }

        // 5.Check employee status, if denied, return fail
        if (emp.getStatus() == 0){
            return R.error("You Have No Access!");
        }

        // 6.Login success, store employee id into Session and return login success
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * Employee log out method
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // Remove employee id from session
        request.getSession().removeAttribute("employee");
        return R.success("Logout successfully");
    }

    /**
     * Add new employee
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("Added a new employee, info: {}", employee.toString());
        // Starter password: 123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employeeService.save(employee);
        if (employeeService.save(employee)) {
            log.info("Employee added successfully");
        } else {
            log.error("Failed to add employee");
        }

        return R.success("Add a new employee");
    }

}
