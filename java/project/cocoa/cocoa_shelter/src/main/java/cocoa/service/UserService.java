package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import cocoa.entity.User;

/**
 * Service interface for managing User entities.
 * Extends the MyBatis-Plus IService for common CRUD operations.
 */
@Service
public interface UserService extends IService<User> {

}
