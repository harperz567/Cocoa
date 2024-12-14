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
    /**
     * The unique identifier for the category.
     */
    private Long id;

    /**
     * The type of category. 1 indicates an animal, and 2 indicates a bonded pair.
     */
    private Integer type;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The order of the category in the list.
     */
    private Integer sort;

    /**
     * The date and time when this category was created.
     * This field is automatically filled during insertion.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * The date and time when this category was last updated.
     * This field is automatically filled during both insertions and updates.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * The ID of the user who created this category.
     * This field is automatically filled during insertion.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * The ID of the user who last updated this category.
     * This field is automatically filled during both insertions and updates.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * Indicates whether this category has been deleted.
     * The field is excluded from selection queries.
     */
    @TableField(select = false)
    private Integer isDeleted;
}