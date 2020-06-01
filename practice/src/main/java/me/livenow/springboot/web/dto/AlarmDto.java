package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmDto {

        private Long alarmId;
        private String heart;
        private String decibel;
        private String tumble;
        private LocalDateTime date;

        public AlarmDto(Alarm alarm) {
            alarmId = alarm.getId();
            heart = alarm.getHeart();
            decibel = alarm.getDecibel();
            tumble = alarm.getTumble();
            date = alarm.getDate();
        }

}
