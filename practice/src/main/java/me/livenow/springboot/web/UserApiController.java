package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mloUser.User;
import me.livenow.springboot.service.posts.MloService;
import me.livenow.springboot.web.dto.MloSaveRequestDto;
import me.livenow.springboot.web.dto.UserDto;
import me.livenow.springboot.web.dto.UserResponseDto;
import me.livenow.springboot.web.dto.UserSaveRequestDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final MloService mloService;

    @PostMapping("/api/v1/user")
    public long save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto){
            return mloService.userSave(userSaveRequestDto);
    }

    @GetMapping("/api/v1/{user_id}")                                            //기기 확인
    public UserResponseDto findMlo(@PathVariable("user_id") Long user_id){
        List<User> findmlos = mloService.findMlo();
    List<UserDto> collect = findmlos.stream()
            .map(u -> new UserDto(u.getName()))
            .collect(Collectors.toList());
        return new UserResponseDto(collect.size(), collect);
}

/*    @PostMapping("/api/v1/{user_id}")
    public long MloSave(@PathVariable("user_id") Long mlo_id, @RequestBody @Valid MloSaveRequestDto mloSaveRequestDto ){

    }*/



}
