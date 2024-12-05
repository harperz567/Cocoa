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

    private Long id;


    // Pet id
    private Long petId;


    // The detail name
    private String name;


    // The datail list
    private String value;


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
