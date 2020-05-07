package me.livenow.springboot.web.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserResponseDto<T> {
    private int count;
    private T data;

}
