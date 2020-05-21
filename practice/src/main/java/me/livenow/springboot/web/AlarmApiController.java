package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.service.AlarmService;
import me.livenow.springboot.web.dto.AlarmSaveRequestDto;
import me.livenow.springboot.web.dto.AlarmSaveResponseDto;
import me.livenow.springboot.web.dto.RecordResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlarmApiController {

    private final AlarmService alarmService;
    private final AlarmRepository alarmRepository;

    //mlo에 알람 저장
    @PostMapping("/api/v1/mlos/{mloName}/alarms")
    public AlarmSaveResponseDto save(@PathVariable("mloName") String name, @RequestBody AlarmSaveRequestDto alarmSaveRequestDto) {
        return  new AlarmSaveResponseDto(alarmService.save(name, alarmSaveRequestDto));

    }

    //alarmId를 통한 recode 확인
    @GetMapping("/api/v1/alarms/{alarmId}/record")
    public RecordResponseDto findRecordByAlarmId(@PathVariable("alarmId") Long id){
        Alarm recordByAlarmId = alarmRepository.findRecordByAlarmId(id);
        return new RecordResponseDto(recordByAlarmId);
    }
}
