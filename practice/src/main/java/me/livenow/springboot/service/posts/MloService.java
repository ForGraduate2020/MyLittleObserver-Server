package me.livenow.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.Record.RecordRepository;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.domain.mloUser.MloUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MloService {
    private final AlarmRepository alarmRepository;
    private final MloRepository mloRepository;
    private final RecordRepository recordRepository;
    private final MloUserRepository mloUserRepository;
}
