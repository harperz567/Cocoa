package cocoa.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * User info
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    // The name of the user
    private String name;


    // Phone number
    private String phone;


    // Gender, 0 represents male, 1 represents female, 2 represents others
    private String sex;


    // email info
    private String idNumber;


    // profile pic
    private String avatar;


    // Status: 0 represents disabled, 1 represents enabled
    private Integer status;
}
