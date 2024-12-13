package cocoa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Represents an employee entity.
 * This class contains information about an employee, including personal details, account details,
 * and metadata such as creation and update timestamps.
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the employee.
     */
    private Long id;

    /**
     * Username for the employee account.
     */
    private String username;

    /**
     * Full name of the employee.
     */
    private String name;

    /**
     * Password for the employee account.
     */
    private String password;

    /**
     * Phone number of the employee.
     */
    private String phone;

    /**
     * Gender of the employee.
     */
    private String sex;

    /**
     * Social Security Number (SSN) or identification number of the employee.
     */
    private String idNumber;

    /**
     * Status of the employee: 0 for inactive, 1 for active.
     */
    private Integer status;

    /**
     * Timestamp for when the employee record was created. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Timestamp for when the employee record was last updated. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * ID of the user who created the employee record. Automatically filled on insert.
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * ID of the user who last updated the employee record. Automatically filled on insert or update.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
