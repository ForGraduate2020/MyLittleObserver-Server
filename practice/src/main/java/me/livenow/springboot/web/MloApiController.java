package me.livenow.springboot.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.service.MloService;
import me.livenow.springboot.web.dto.MloDto;
import me.livenow.springboot.web.dto.MloSaveRequestDto;
import me.livenow.springboot.web.dto.MloSaveResponseDto;
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
    @PostMapping("/api/v1/users/{userName}/mlos")
    public MloSaveResponseDto save(@PathVariable("userName") String name, @RequestBody @Valid MloSaveRequestDto mloSaveRequestDto) {
        return new MloSaveResponseDto(mloService.mloSave(name, mloSaveRequestDto));
    }

    //지정 mlo의 정보, 알람 보기
    @GetMapping("/api/v1/mlos/{mloName}")
    public List<MloDto> findAllAlarmsByMlo(@PathVariable("mloName") String mloName) {
        List<Mlo> alarmByMlo = mloRepository.findAlarmByMlo(mloName);
        List<MloDto> collect = alarmByMlo.stream()
                .map(m -> new MloDto(m))
                .collect(Collectors.toList());
        return collect;
    }
}
