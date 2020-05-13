package me.livenow.springboot.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mloUser.User;
import me.livenow.springboot.domain.mloUser.UserRepository;
import me.livenow.springboot.service.posts.MloService;
import me.livenow.springboot.web.dto.UserSaveRequestDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final MloService mloService;
    private final UserRepository userRepository;

    @PostMapping("/api/v1/user")
    public long save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto){
            return mloService.userSave(userSaveRequestDto);
    }

    @GetMapping("/api/v1/users") //mlo 확인
    public List<UserDto> mlosV1( @RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<User> users = userRepository.findAllWithMlos(offset, limit);

        List<UserDto> collect = users.stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());

        return collect;


    }

    @Data
    static class UserDto{
        private Long userId;
        private String name;
        private List<UserMloDto> mlos;

        public UserDto(User user) {
            userId = user.getId();
            name = user.getName();
            mlos = user.getMlos().stream()
                    .map(mlo -> new UserMloDto(mlo))
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class UserMloDto {
        private String name; //user 이름름
        private String mloName; //mlo 이름

        public UserMloDto(Mlo mlo) {
            name = mlo.getUser().getName();
            mloName = mlo.getMloName();
        }
    }
}






