package cocoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cocoa.entity.PetDetail;
/**
 * Service interface for managing pet details.
 * This interface extends the MyBatis-Plus `IService` interface, providing CRUD operations for the PetDetail entity.
 * It is used for handling operations related to the `PetDetail` table, which contains additional information
 * related to pets in the system.
 *
 * <p>Implementations of this interface can use MyBatis-Plus to perform operations like saving,
 * updating, retrieving, and deleting pet details.</p>
 */
public interface PetDetailService extends IService<PetDetail> {

}
