package com.livenow.fileupdownloaddemo.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
@Getter
@Setter
public class FileUploadProperties {
    private String uploadDir;

}
