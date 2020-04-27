package com.livenow.fileupdownloaddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    @GetMapping("/")
    public String controllerMain(){
        return "Hlleo~~";
    }
}
