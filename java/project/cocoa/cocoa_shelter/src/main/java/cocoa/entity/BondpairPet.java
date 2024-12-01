package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 宠物家族宠物关系
 */
@Data
public class BondpairPet implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //宠物家族id
    private Long bondpairId;


    //宠物id
    private Long petId;


    //宠物名称 （冗余字段）
    private String name;

    //宠物原价
    private BigDecimal price;

    //份数
    private Integer copies;


    //排序
    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    private Integer isDeleted;
}
