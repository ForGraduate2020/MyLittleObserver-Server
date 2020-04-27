package com.livenow.fileupdownloaddemo;

import com.livenow.fileupdownloaddemo.property.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileUploadProperties.class
})
public class FileUpDownloadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUpDownloadDemoApplication.class, args);
	}

}
