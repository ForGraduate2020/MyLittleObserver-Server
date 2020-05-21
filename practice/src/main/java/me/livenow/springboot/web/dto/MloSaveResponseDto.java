package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MloSaveResponseDto {

    private Long mloId;
    private String message;

    public MloSaveResponseDto(Long id) {
        this.mloId = id;
        message="mlo 등록 성공";
    }
}
