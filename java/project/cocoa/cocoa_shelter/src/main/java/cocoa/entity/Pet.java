package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Represents a pet entity.
 * This class contains information about a pet available for adoption, including its name, category, price,
 * status, and other metadata.
 */
@Data
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the pet.
     */
    private Long id;

    /**
     * Name of the pet.
     */
    private String name;

    /**
     * Category ID associated with the pet.
     */
    private Long categoryId;

    /**
     * Adoption fee for the pet.
     */
    private BigDecimal price;

    /**
     * A code representing additional information about the pet (purpose unspecified).
     */
    private String code;

    /**
     * URL or path to the picture of the pet.
     */
    private String image;

    /**
     * Description of the pet.
     */
    private String description;

    /**
     * Status of the pet: 0 for ordered, 1 for up for adoption.
     */
    private Integer status;

    /**
     * Sorting order for displaying the pet.
     */
    private Integer sort;

    /**
     * Timestamp for when the pet was created. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Timestamp for when the pet was last updated. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * ID of the user who created the pet. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * ID of the user who last updated the pet. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * Indicates whether the pet is deleted. 1 for deleted, 0 for not deleted.
     */
    private Integer isDeleted;
}
