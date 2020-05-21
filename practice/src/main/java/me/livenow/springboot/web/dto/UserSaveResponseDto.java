package me.livenow.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveResponseDto {
    private Long id;

    public UserSaveResponseDto(Long id) {
        this.id = id;
    }
}
