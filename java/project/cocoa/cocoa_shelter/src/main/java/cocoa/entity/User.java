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
    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The full name of the user.
     */
    private String name;

    /**
     * The phone number of the user.
     */
    private String phone;

    /**
     * The gender of the user.
     * 0 represents Female and 1 represents Male.
     */
    private String sex;

    /**
     * The identification number of the user.
     */
    private String idNumber;

    /**
     * The avatar URL or image associated with the user.
     */
    private String avatar;

    /**
     * The status of the user.
     * 0 means banned and 1 means normal.
     */
    private Integer status;
}