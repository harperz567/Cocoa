package cocoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cocoa.entity.User;
import cocoa.mapper.UserMapper;
import cocoa.service.UserService;

/**
 * Implementation of the UserService interface.
 * Provides CRUD operations for User entities using MyBatis-Plus.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
