package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Represents a bonded pair pet entity.
 * This class contains information about a pet that is part of a bonded pair, including its ID, name,
 * price, and metadata such as creation and update timestamps.
 */
@Data
public class BondpairPet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the bonded pair pet.
     */
    private Long id;

    /**
     * ID of the bonded pair to which this pet belongs.
     */
    private Long bondpairId;

    /**
     * ID of the pet.
     */
    private Long petId;

    /**
     * Name of the pet (redundant field).
     */
    private String name;

    /**
     * Original price of the pet.
     */
    private BigDecimal price;

    /**
     * Number of copies of this pet in the bonded pair.
     */
    private Integer copies;

    /**
     * Sorting order for the pet within the bonded pair.
     */
    private Integer sort;

    /**
     * Timestamp for when the bonded pair pet was created. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Timestamp for when the bonded pair pet was last updated. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * ID of the user who created the bonded pair pet. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * ID of the user who last updated the bonded pair pet. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * Indicates whether the bonded pair pet is deleted. 1 for deleted, 0 for not deleted.
     */
    private Integer isDeleted;
}
