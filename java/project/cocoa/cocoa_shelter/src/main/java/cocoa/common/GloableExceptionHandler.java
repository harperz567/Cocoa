package cocoa.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global exception handler.
 * Implements centralized exception handling for controllers using AOP.
 * When an exception occurs in the application, it will be intercepted and handled here.
 *
 * Annotations:
 * - @ControllerAdvice: Specifies the controllers to intercept.
 * - @ControllerAdvice(annotations = {RestController.class, Controller.class}):
 *   Targets controllers annotated with @RestController or @Controller.
 * - @ResponseBody: Ensures the returned object is serialized into JSON for HTTP responses.
 * - @Slf4j: Enables logging within the class.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j

public class GloableExceptionHandler {

    /**
     * Handles SQLIntegrityConstraintViolationException and NullPointerException.
     *
     * @param exception The exception object, used to retrieve details of the error.
     * @return A custom error response encapsulated in the Result object.
     *         If the error involves a duplicate entry, a specific message is returned.
     *         Otherwise, a general "unknown error" message is returned.
     */
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, NullPointerException.class})
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        log.error(exception.getMessage());

        if (exception.getMessage().contains("Duplicate entry")) {

            String[] splitErrorMessage = exception.getMessage().split(" ");

            String errorMessage = "This account already exists: " + splitErrorMessage[2];
            return R.error(errorMessage);
        }
        return R.error("unknown error");
    }

    /**
     * Handles CustomException.
     *
     * @param ex The custom exception object containing specific error information.
     * @return A custom error response encapsulated in the Result object with the exception message.
     */
    @ExceptionHandler({CustomException.class})
    public R<String> exceptionHandlerCustomer(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}