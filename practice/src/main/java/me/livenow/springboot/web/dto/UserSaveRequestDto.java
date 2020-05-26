package me.livenow.springboot.web.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    @NotEmpty
    private String username;
}
