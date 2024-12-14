package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 Pet Details
 */
@Data
public class PetDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The unique identifier for the pet detail.
     */
    private Long id;

    /**
     * The unique identifier for the associated pet.
     * This links the detail to a specific pet in the system.
     */
    private Long petId;

    /**
     * The name of the pet detail (e.g., "Breed", "Color").
     */
    private String name;

    /**
     * The value of the pet detail (e.g., "Golden Retriever", "Brown").
     */
    private String value;

    /**
     * The date and time when this pet detail was created.
     * This field is automatically filled during insertion.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * The date and time when this pet detail was last updated.
     * This field is automatically filled during both insertions and updates.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * The ID of the user who created this pet detail.
     * This field is automatically filled during insertion.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * The ID of the user who last updated this pet detail.
     * This field is automatically filled during both insertions and updates.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * Indicates whether this pet detail has been deleted.
     * This field is used for logical deletion of records and is not included in select queries.
     */
    private Integer isDeleted;
}