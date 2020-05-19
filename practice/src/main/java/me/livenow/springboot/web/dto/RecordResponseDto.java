package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.record.Record;

@Getter
public class RecordResponseDto {
    private Long id;
    private String fileName;
    private byte[] data;

    public RecordResponseDto(Alarm alarm) {
        id = alarm.getRecord().getId();
        fileName = alarm.getRecord().getFileName();
        data=alarm.getRecord().getData();
    }
}
