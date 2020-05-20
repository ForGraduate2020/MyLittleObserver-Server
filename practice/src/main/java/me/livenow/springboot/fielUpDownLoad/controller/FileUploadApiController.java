package me.livenow.springboot.fielUpDownLoad.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.record.Record;
import me.livenow.springboot.domain.record.RecordRepository;
import me.livenow.springboot.fielUpDownLoad.payload.FileUploadResponse;
import me.livenow.springboot.fielUpDownLoad.service.FileUploadDownloadService;
import me.livenow.springboot.service.posts.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class FileUploadApiController {

    private static  Logger logger = LoggerFactory.getLogger(FileUploadApiController.class);
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private  RecordService recordService;

    @Autowired
    private FileUploadDownloadService service;

    //mloName을 통한 uploads
    @PostMapping("/api/v1/{mloName}/uploadFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("mloName") String mloName) {
        String fileName = service.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        recordService.save(fileName, fileDownloadUri, file.getContentType(), file.getSize(), mloName);

        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    //fileName을 통한 fileUrl가져오기
    @GetMapping("/api/v1/downloadFile/{fileName:.+}")
    public List<FileDto> downloadFile(@PathVariable String fileName) {
        List<Record> allByfileName = recordRepository.findAllByFileName(fileName);

        List<FileDto> collect = allByfileName.stream()
                .map(r -> new FileDto(r))
                .collect(Collectors.toList());

        return collect;
    }

    @Data
    static class FileDto {
        private String fileDownloadUrl;


        public FileDto(Record record) {
            fileDownloadUrl = record.getFileDownloadUrl();
        }
    }



/*    @GetMapping("/api/v1/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        // Load file as Resource
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/
}