package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Pets class
 */
@Data
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // Pet name
    private String name;

    //Pet id
    private Long categoryId;

    // Adoption fee
    private BigDecimal price;

    // A code(What's this?????????????)
    private String code;

    // Picture of the pet
    private String image;

    // Pet description
    private String description;

    // 0: Ordered 1: Up for adoption
    private Integer status;

    // Order
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    // Whether it is deleted
    private Integer isDeleted;

}
