package me.livenow.springboot.service;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.web.dto.AlarmSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MloRepository mloRepository;

    @Transactional
    public Long save(String name, AlarmSaveRequestDto alarmSaveRequestDto) {
        List<Mlo> mloByNames = mloRepository.findByName(name);
        if(mloByNames.isEmpty())
            throw new IllegalStateException("등록된 mlo가 없습니다.");

        Alarm alarm = new Alarm();
        alarm.setHeart(alarmSaveRequestDto.getHeart());
        alarm.setDecibel(alarmSaveRequestDto.getDecibel());
        alarm.setTumble(alarmSaveRequestDto.getTumble());
        alarm.setDate(LocalDateTime.now());
        alarm.setMlo(mloByNames.remove(0));

        alarmRepository.save(alarm);
        return alarm.getId();
    }

    public List<Alarm> findAllAlarmsByMloName(String mloName, int offset, int limit) {
        Mlo mlo = mloRepository.finOnedByName(mloName);
        if(mlo==null)
            throw new IllegalStateException("등록된 mloName이 아닙니다.");

        return alarmRepository.findAllAlarmsByMloName(mloName, offset, limit);

    }
}
