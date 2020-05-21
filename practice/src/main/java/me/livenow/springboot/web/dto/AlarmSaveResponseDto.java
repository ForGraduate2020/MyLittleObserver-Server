package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmSaveResponseDto {
    private Long alarmId;
    private String message;

    public AlarmSaveResponseDto(Long id) {
        this.alarmId = id;
        message="알람이 등록 되었습니다.";
    }
}
