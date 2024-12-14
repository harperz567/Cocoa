package cocoa.common;
/**
 * A custom exception class that extends RuntimeException.
 * This can be used to handle application-specific runtime exceptions.
 */
public class CustomException extends RuntimeException{
    /**
     * Constructs a new CustomException with the specified detail message.
     *
     * @param message the detail message to describe the exception
     */
    public CustomException(String message){
        super(message);
    }
}
