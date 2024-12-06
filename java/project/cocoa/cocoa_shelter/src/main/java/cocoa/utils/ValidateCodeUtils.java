package cocoa.utils;

import java.util.Random;

/**
 * Utility class for generating random verification codes.
 * <p>This class provides methods to generate numeric and string-based verification codes
 * of specified lengths (either 4 or 6 digits for numeric codes).
 */
public class ValidateCodeUtils {
    /**
     * Generates a random numeric verification code of the specified length.
     *
     * <p>The method ensures that the generated code is either a 4-digit or 6-digit number.
     * If the generated number is less than the required number of digits, it adds leading zeros.
     *
     * @param length The length of the verification code (4 or 6).
     * @return The generated verification code as an integer.
     * @throws RuntimeException if the length is not 4 or 6.
     */
    public static Integer generateValidateCode(int length){
        Integer code =null;
        if(length == 4){
            code = new Random().nextInt(9999);// Generate a random number, max 9999
            if(code < 1000){
                code = code + 1000; // Ensure the number is a 4-digit code
            }
        }else if(length == 6){
            code = new Random().nextInt(999999);// Generate a random number, max 999999
            if(code < 100000){
                code = code + 100000;// Ensure the number is a 6-digit code
            }
        }else{
            throw new RuntimeException("Only 4 or 6 digit numeric verification codes are allowed");
        }
        return code;
    }

    /**
     * Generates a random alphanumeric verification code of the specified length.
     *
     * <p>This method generates a hexadecimal string and returns a substring of the specified length.
     *
     * @param length The length of the verification code.
     * @return The generated verification code as a string.
     */
    public static String generateValidateCode4String(int length){
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        String capstr = hash1.substring(0, length);
        return capstr;
    }
}
