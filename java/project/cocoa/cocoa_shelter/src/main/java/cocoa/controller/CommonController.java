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

/**
 * CommonController is a REST controller that provides endpoints for file upload and file download functionality.
 * It allows uploading images to a specified location and downloading images for preview purposes.
 *
 * <p>The file paths are configured in the application properties file and accessed using @Value annotation.</p>
 *
 * <p>Logging is provided using Lombok's @Slf4j annotation.</p>
 *
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    /**
     * The base file storage path configured in the application properties file.
     */
    @Value("${cocoa.path}")
    private String basePath;

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
    /**
     * Handles image upload requests.
     * <p>This method processes the uploaded file, generates a unique file name with the original extension,
     * stores it in the specified directory, and returns the generated file name for further use.</p>
     *
     * @param file the file uploaded by the client
     * @return a {@link R} containing the generated file name
     */
    @PostMapping("/upload")
    public R<String> upLoadFile(MultipartFile file) {
        log.info("Received file: {}", file.toString());

        // Extract the original file name and determine the file extension
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));

        // Generate a unique file name using UUID
        String fileName = UUID.randomUUID().toString() + suffix;

        // Ensure the target directory exists
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs(); // Create directory if it doesn't exist
        }

        try {
            // Save the file to the target directory
            file.transferTo(new File(basePath + "img/" +fileName));
        } catch (IOException e) {
            log.error("Error occurred while saving the file", e);
        }

        // Return the file name for future reference
        return R.success(fileName);
    }

    /**
     * Handles requests to download and preview an uploaded file.
     * <p>This method reads the file from the server's storage and writes it to the HTTP response output stream
     * to allow the client to view or download the file.</p>
     *
     * @param httpServletResponse the HTTP response object used to send the file
     * @param name                the name of the file to be downloaded
     * @throws IOException if an I/O error occurs during file reading or writing
     */
    @GetMapping("/download")
    public void fileDownload(HttpServletResponse httpServletResponse, String name) {
        try {
            // Read the file from the specified path
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + "img/" +name));

            // Get the output stream from the HTTP response
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();

            // Set the response content type to indicate an image is being sent
            httpServletResponse.setContentType("image/jpeg");

            // Buffer for reading the file in chunks
            byte[] fileArray = new byte[1024];
            int length;

            // Write the file content to the response output stream
            while ((length = fileInputStream.read(fileArray)) != -1) {
                outputStream.write(fileArray, 0, length);
                outputStream.flush();
            }

            // Close the resources
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            log.error("Error occurred while downloading the file", e);
        }
    }
}
