package cocoa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
/**
 * Main application class for the Cocoa project.
 *
 * <p>This class initializes and runs the Spring Boot application.
 * It includes configurations for component scanning, transaction management,
 * and servlet components.
 *
 * <p>By default, the application serves static resources from the 'static' or 'template' folders.
 */
// With @Slf4j, we can use getters, setters, log
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class CocoaApp {
    /**
     * The main method to launch the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args){
        SpringApplication.run(CocoaApp.class, args);
        log.info("Project Successfully Launched!!!!!");
    }
}

// By default, we can only read static src from 'static'/'templet' folder

