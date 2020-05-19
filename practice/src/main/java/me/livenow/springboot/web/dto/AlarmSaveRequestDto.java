package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AlarmSaveRequestDto {

    private String heart;
    private String decibel;
    private String tumble;
}
