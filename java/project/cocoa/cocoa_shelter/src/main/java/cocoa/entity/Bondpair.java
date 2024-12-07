package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Bonded pair class
 */
@Data
public class Bondpair implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    // Category id
    private Long categoryId;


    // Bonded pair name
    private String name;


    // Adoption fee
    private BigDecimal price;


    // 0: Ordered 1: Up for adoption
    private Integer status;


    // Represent the status
    private String code;


    // Pair description
    private String description;


    // Picture
    private String image;


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
