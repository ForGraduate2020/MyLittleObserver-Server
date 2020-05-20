package me.livenow.springboot.web.dto;

import lombok.Getter;
import me.livenow.springboot.domain.alarm.Alarm;




@Getter
public class RecordResponseDto {
    private Long id;
    private String fileName;
    private String fileDownloadUrl;
    private String fileType;


    public RecordResponseDto(Alarm alarm) {
        id = alarm.getRecord().getId();
        fileName = alarm.getRecord().getFileName();
        fileDownloadUrl=alarm.getRecord().getFileDownloadUrl();
        fileType =alarm.getRecord().getFileType();
    }
}
