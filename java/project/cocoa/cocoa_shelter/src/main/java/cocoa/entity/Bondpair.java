package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Represents a bonded pair entity.
 * This class contains information about a bonded pair available for adoption,
 * including its category, name, price, status, and other metadata.
 */
@Data
public class Bondpair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the bonded pair.
     */
    private Long id;

    /**
     * Category ID associated with the bonded pair.
     */
    private Long categoryId;

    /**
     * Name of the bonded pair.
     */
    private String name;

    /**
     * Adoption fee for the bonded pair.
     */
    private BigDecimal price;

    /**
     * Status of the bonded pair: 0 for ordered, 1 for up for adoption.
     */
    private Integer status;

    /**
     * Code representing additional status or identifier for the bonded pair.
     */
    private String code;

    /**
     * Description of the bonded pair.
     */
    private String description;

    /**
     * Image URL or path for the bonded pair.
     */
    private String image;

    /**
     * Timestamp for when the bonded pair was created. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Timestamp for when the bonded pair was last updated. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * ID of the user who created the bonded pair. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * ID of the user who last updated the bonded pair. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * Indicates whether the bonded pair is deleted. 1 for deleted, 0 for not deleted.
     */
    private Integer isDeleted;
}
