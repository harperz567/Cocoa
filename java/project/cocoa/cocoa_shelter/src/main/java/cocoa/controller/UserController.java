package cocoa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cocoa.common.R;
import cocoa.entity.User;
import cocoa.service.UserService;
import cocoa.utils.ValidateCodeUtils;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired

    private UserService userService ;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // Get phone number
        String phone = user.getPhone();

        if (phone != null && !phone.trim().isEmpty()){
            // Generate 4 digit confirming code
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("Phone:{}   Code:{}",phone,code);

            // Use cloud service to send a message
            // SMSUtils.sendMessage("CompanyName", "", phone, code)

            // Save the confirming code to session
            session.setAttribute(phone, code);
            return R.success("Message sent successfully!");
        }
        return R.error("Message failed to sent!");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());
        // Get phone number
        String phone = map.get("phone").toString();
        // Get confirming code
        String code = map.get("code").toString();
        // Get confirming code from session
        Object codeInSession = session.getAttribute(phone);
        // Compare confirming code
        if (codeInSession != null && codeInSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            //queryWrapper.eq("phone",phone);
            User user = userService.getOne(queryWrapper);
            //User user = userService.list(queryWrapper).get(0);
            if (user == null){
                // Check if it's a new user
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("Login Failed");
    }

}
