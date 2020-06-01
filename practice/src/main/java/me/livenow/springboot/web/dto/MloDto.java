package me.livenow.springboot.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MloDto {

        private Long mloId;
        private String mloName;
        private List<AlarmDto> alarms;

        public MloDto(Mlo mlo) {
            mloId = mlo.getId();
            mloName = mlo.getMloName();
            alarms = mlo.getAlarms().stream()
                    .map(alarm -> new AlarmDto(alarm))
                    .collect(Collectors.toList());
        }
}
