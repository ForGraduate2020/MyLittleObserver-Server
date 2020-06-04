package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmDeleteResponseDto {
    private String message;

    public AlarmDeleteResponseDto(String message) {
        this.message = message;
    }
}
