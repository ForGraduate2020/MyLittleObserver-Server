package me.livenow.springboot.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.service.posts.MloService;
import me.livenow.springboot.web.dto.MloSaveRequestDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MloApiController {
    private final MloService mloService;
    private final MloRepository mloRepository;


    //유저 이름을 통한 mlo 등록 기능
    @PostMapping("/api/v1/mlo/{userName}")
    public long save(@PathVariable("userName") String name, @RequestBody @Valid MloSaveRequestDto mloSaveRequestDto) {
        return mloService.mloSave(name, mloSaveRequestDto);
    }

    //mlo의 정보, 알람 보기
    @GetMapping("/api/v1/mlo/{mloName}")
    public List<MloDto> findAllAlarmsByMlo(@PathVariable("mloName") String mloName,
                                           @RequestParam(value = "offset", defaultValue = "0") int offset,
                                           @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Mlo> alarmByMlo = mloRepository.findAlarmByMlo(mloName, offset, limit);
        List<MloDto> collect = alarmByMlo.stream().map(m -> new MloDto(m)).collect(Collectors.toList());
        return collect;
    }


    @Data
    static class MloDto {
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

    @Data
    static class AlarmDto {
        private Long alarmId;
        private String heart;
        private String decibel;
        private String tumble;
        private LocalDateTime date;

        public AlarmDto(Alarm alarm) {
            alarmId = alarm.getId();
            heart = alarm.getHeart();
            decibel = alarm.getDecibel();
            tumble = alarm.getTumble();
            date = alarm.getDate();
        }
    }

}

