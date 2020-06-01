package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.mloUser.User;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindMloByUserResponseDto {

        private Long userId;
        private String name;
        private List<FindUserByMloDto> mlos;

        public FindMloByUserResponseDto(User user) {
            userId = user.getId();
            name = user.getName();
            mlos = user.getMlos().stream()
                    .map(mlo -> new FindUserByMloDto(mlo))
                    .collect(Collectors.toList());
        }
}
