package cocoa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
// With @Slf4j, we can use getters, setters, log
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class CocoaApp {
  public static void main(String[] args){
    SpringApplication.run(CocoaApp.class, args);
    log.info("Project Successfully Launched!!!!!");
  }
}

// By default, we can only read static src from 'static'/'templet' folder

