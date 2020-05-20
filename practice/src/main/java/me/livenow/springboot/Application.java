package me.livenow.springboot;

import me.livenow.springboot.fileUpDownLoad.property.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableJpaAuditing //가 삭제됨
@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
