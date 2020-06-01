package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.mloUser.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllUserResponseDto {

        private Long userId;
        private String name;

        public AllUserResponseDto(User user) {
            this.userId = user.getId();
            this.name = user.getName();
        }
}
