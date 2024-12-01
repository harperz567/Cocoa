package cocoa.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cocoa.common.R;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${cocoa.path}")
    private String basePath;

    /**
     * Upload file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // File is temperoray, need to be transfer into another directory, or it will be destroried after request
        log.info(file.toString());

        // Original file name
        String orginalFileName = file.getOriginalFilename();
        String suffix = orginalFileName.substring(orginalFileName.lastIndexOf("."));

        // Use UUID to generate new file name
        String fileName = UUID.randomUUID().toString() + suffix;

        // Create new directory
        File dir = new File(basePath);
        if (!dir.exists()){
            // If directory doesn't exist, create it
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    /**
     * Download file
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            // Input Stream, we get file content through input stream
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            // Output stream, we write file content to chrome and display file through output stream
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            // Close resourses
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
