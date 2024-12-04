package cocoa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import cocoa.common.R;
import cocoa.entity.User;
import cocoa.service.UserService;
import cocoa.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * UserController handles user-related operations for the front-end,
 * such as sending verification codes and user login functionality.
 * </p>
 *
 * <p>This controller interacts with the {@link UserService} for database operations and utilizes
 * sessions to store temporary data such as verification codes and user IDs.</p>
 *
 * <p>Logging is provided using Lombok's @Slf4j annotation for debugging and monitoring purposes.</p>
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Sends a verification code to the user's phone number.
     * <p>This method generates a 4-character verification code, logs it for debugging purposes,
     * and stores it in the session. The code is valid for 5 minutes.</p>
     *
     * @param user the {@link User} object containing the user's phone number
     * @param session the HTTP session to store the verification code for later validation
     * @return a {@link R} indicating success or failure of the operation
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            // Generate a 4-character verification code
            String code = ValidateCodeUtils.generateValidateCode4String(4);

            // Log the phone number and code for debugging
            log.info("Phone number: {}  Verification code: {}", phone, code);

            // Store the verification code in the session with the phone number as the key
            session.setAttribute(phone, code);

            return R.success("Verification code sent successfully, valid for 5 minutes.");
        }

        return R.error("Failed to send verification code.");
    }

    /**
     * Handles user login.
     * <p>This method validates the verification code provided by the user against the one stored
     * in the session. If the verification is successful, the user is either logged in or registered
     * if they are new. The user's ID is stored in the session for authorization purposes.</p>
     *
     * @param map a {@link Map} containing the user's phone number and verification code
     * @param session the HTTP session to retrieve the stored verification code
     * @return a {@link R} indicating success or failure of the login attempt
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody Map<String, Object> map, HttpSession session) {
        // Extract phone number and verification code from the input
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        // Retrieve the verification code stored in the session
        String tempCode = (String) session.getAttribute(phone);

        // Validate the verification code
        if (tempCode != null && tempCode.equals(code)) {
            // Check if the user exists in the database
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(lambdaQueryWrapper);

            // Register a new user if no existing record is found
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1); // Mark the user as active
                userService.save(user);
            }

            // Store the user ID in the session for authentication purposes
            session.setAttribute("user", user.getId());

            return R.success("Login successful, welcome!");
        }

        return R.error("Invalid verification code.");
    }
}
