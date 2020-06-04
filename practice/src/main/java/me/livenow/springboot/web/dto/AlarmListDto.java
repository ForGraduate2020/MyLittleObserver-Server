package me.livenow.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmListDto {

    private Long alarm_id;
    private String heart;
    private String decibel;
    private String tumble;
    private LocalDateTime date;

    public AlarmListDto(Alarm alarm) {
        alarm_id= alarm.getId();
        heart = alarm.getHeart();
        decibel = alarm.getDecibel();
        tumble = alarm.getTumble();
        date = alarm.getDate();
    }
}
