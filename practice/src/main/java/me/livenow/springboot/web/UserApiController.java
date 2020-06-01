package me.livenow.springboot.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mloUser.User;
import me.livenow.springboot.domain.mloUser.UserRepository;
import me.livenow.springboot.service.UserService;
import me.livenow.springboot.web.dto.AllUserResponseDto;
import me.livenow.springboot.web.dto.FindMloByUserResponseDto;
import me.livenow.springboot.web.dto.UserSaveRequestDto;
import me.livenow.springboot.web.dto.UserSaveResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;

    //user등록
    @PostMapping("/api/v1/users")
    public UserSaveResponseDto save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto){
            return new UserSaveResponseDto(userService.userSave(userSaveRequestDto));
    }

    // 모든 user 이름,id 조회
    @GetMapping("/api/v1/users")
    public List<AllUserResponseDto> findAllUser(){
        List<User> all = userRepository.findAll();
        List<AllUserResponseDto> collect = all.stream().map(u -> new AllUserResponseDto(u)).collect(Collectors.toList());
        return collect;
    }

    //user 이름으로  mlo 조회(mlo가 없을 시 조회 안됨 )
    @GetMapping("/api/v1/users/{userName}")
    public List<FindMloByUserResponseDto> findOneUser(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                      @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                      @PathVariable("userName") String name){
        List<User> user = userRepository.findMloByName(name, offset, limit);
        List<FindMloByUserResponseDto> collect = user.stream()
                .map(u -> new FindMloByUserResponseDto(u))
                .collect(Collectors.toList());
        if(collect.isEmpty())
          throw new IllegalStateException("사용자 " + name + "에 기걔가 등록되어 있지 않습니다.");

        return collect;
    }

    @GetMapping("/api/v1/users/mlos") //모든 user의 mlo 확인
    public List<FindMloByUserResponseDto> mlosV1(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                 @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<User> users = userRepository.findAllWithMlos(offset, limit);
        List<FindMloByUserResponseDto> collect = users.stream()
                .map(u -> new FindMloByUserResponseDto(u))
                .collect(Collectors.toList());
        return collect;
    }
}






