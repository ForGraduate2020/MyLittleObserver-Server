package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.fcm.AndroidPushNotificationsService;
import me.livenow.springboot.fcm.AndroidPushPeriodicNotifications;
import me.livenow.springboot.service.AlarmService;
import me.livenow.springboot.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AlarmApiController {

    private final AlarmService alarmService;
    private final AlarmRepository alarmRepository;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    //mlo에 알람 저장
    @PostMapping("/api/v1/mlos/{mloName}/alarms")
    public AlarmSaveResponseDto save(@PathVariable("mloName") String name, @RequestBody AlarmSaveRequestDto alarmSaveRequestDto) {

        String notifications = AndroidPushPeriodicNotifications.alarmSaveNotification();
        HttpEntity<String> request = new HttpEntity<>(notifications);
        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        return new AlarmSaveResponseDto(alarmService.save(name, alarmSaveRequestDto));
    }

/*    //alarmId를 통한 recode 확인
    @GetMapping("/api/v1/alarms/{alarmId}/record")
    public RecordResponseDto findRecordByAlarmId(@PathVariable("alarmId") Long id){
        Alarm recordByAlarmId = alarmRepository.findRecordByAlarmId(id);
        return new RecordResponseDto(recordByAlarmId);
    }*/

    //mloName을 통한 모든 알람 검색
    @GetMapping("/api/v1/alarms/{mloName}")
    public List<AlarmListDto> findAllAlarmsByMloName(@PathVariable("mloName") String mloName,
                                                        @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                        @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<Alarm> allAlarms = alarmService.findAllAlarmsByMloName(mloName, offset, limit);

        List<AlarmListDto> collect = allAlarms.stream()
                .map(a -> new AlarmListDto(a))
                .collect(Collectors.toList());

        return collect;
    }

    //알람삭제
    @DeleteMapping("/api/v1/alarms/{alarmId}")
    public AlarmDeleteResponseDto delete(@PathVariable("alarmId") Long id){
        String deleteMessage = alarmService.delete(id);
        return new AlarmDeleteResponseDto(deleteMessage);
    }

}
