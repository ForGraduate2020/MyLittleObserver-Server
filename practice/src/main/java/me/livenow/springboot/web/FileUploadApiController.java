package me.livenow.springboot.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.record.Record;
import me.livenow.springboot.domain.record.RecordRepository;
import me.livenow.springboot.payload.FileUploadResponse;
import me.livenow.springboot.service.FileUploadDownloadService;
import me.livenow.springboot.service.RecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class FileUploadApiController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadApiController.class);
    private final RecordRepository recordRepository;
    private final RecordService recordService;

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
    @GetMapping("/api/v1/fileUrl/{fileName:.+}")
    public List<FileDto> downloadFile(@PathVariable String fileName) {
        List<Record> allByFileName = recordRepository.findAllByFileName(fileName);
        if(allByFileName.isEmpty())
            throw new IllegalStateException("등록된 file이 아닙니다.");

        List<FileDto> collect = allByFileName.stream()
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

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable String fileName, HttpServletRequest request){
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
    }
}