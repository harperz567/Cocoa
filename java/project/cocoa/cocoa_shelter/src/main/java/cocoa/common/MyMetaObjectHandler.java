package cocoa.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


@Component
@Slf4j
/**
 * MyMetaObjectHandler is a custom implementation of the MetaObjectHandler interface for handling
 * automatic filling of common fields during database insert and update operations in MyBatis-Plus.
 *
 * Functionality:
 * - Automatically populates `createTime`, `updateTime`, `createUser`, and `updateUser` fields during `insert` operations.
 * - Automatically populates `updateTime` and `updateUser` fields during `update` operations.
 *
 * Annotations:
 * - @Component: Marks this class as a Spring-managed component.
 * - @Slf4j: Enables logging functionality.
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * Automatically fills common fields during an `insert` operation.
     *
     * @param metaObject The MetaObject instance representing the entity being inserted.
     *                   This allows access to fields for auto-filling.
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Common attribute auto fill[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());

    }
    /**
     * Automatically fills common fields during an `update` operation.
     *
     * @param metaObject The MetaObject instance representing the entity being updated.
     *                   This allows access to fields for auto-filling.
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Common attribute auto fill[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
