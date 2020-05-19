package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.service.posts.AlarmService;
import me.livenow.springboot.web.dto.AlarmSaveRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlarmApiController {

    private final AlarmService alarmService;
    private final AlarmRepository alarmRepository;

    @PostMapping("/api/v1/mlos/{mloName}/alarms")
    public long save(@PathVariable("mloName") String name, @RequestBody AlarmSaveRequestDto alarmSaveRequestDto) {
        return alarmService.save(name, alarmSaveRequestDto);
    }
}
