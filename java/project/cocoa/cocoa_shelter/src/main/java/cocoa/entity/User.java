package cocoa.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * Represents user information.
 * This class is used as an entity for the User table.
 * <p>
 * Fields include basic user details like name, phone, gender, and status.
 * Implements {@link Serializable} for object serialization.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;



    private Long id;


    private String name;



    private String phone;


    //SEX 0 Female 1 male
    private String sex;


    private String idNumber;


    private String avatar;


    //Status 0:banned，1:normal
    private Integer status;
}
