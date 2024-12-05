package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Animal category, an animal should have the following fields: an id, a type,
 * a name, sort, createTime, updateTime, createUser, updateUser, isDeleted.
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // Animal/bonded pair. 1 indicates animal type, 2 indicates pair type.
    private Integer type;

    // Name of the category
    private String name;

    // The order of the category
    private Integer sort;

    // When this category was created
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // When this category was updated
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // Who created this category
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    // Who updated this category
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    // Whether this category is deleted
    @TableField(select = false)
    private Integer isDeleted;
}
