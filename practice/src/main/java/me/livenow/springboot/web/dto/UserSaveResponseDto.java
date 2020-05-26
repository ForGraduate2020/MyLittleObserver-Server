package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveResponseDto {

    private Long id;
    private String message;

    public UserSaveResponseDto(Long id) {
        this.id = id;
        message="user 등록 성공";
    }
}
