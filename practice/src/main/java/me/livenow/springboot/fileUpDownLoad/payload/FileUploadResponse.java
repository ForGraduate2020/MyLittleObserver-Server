package me.livenow.springboot.fileUpDownLoad.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FileUploadResponse {
    private final String fileName;
    private final String fileDownloadUrl;
    private final String fileType;
    private final long size;
}
