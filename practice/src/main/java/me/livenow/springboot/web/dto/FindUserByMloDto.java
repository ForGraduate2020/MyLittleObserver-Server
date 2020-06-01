package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindUserByMloDto {

        private Long mloId; //user 이름름
        private String mloName; //mlo 이름

        public FindUserByMloDto(Mlo mlo) {
            mloId = mlo.getId();
            mloName = mlo.getMloName();
        }

}
