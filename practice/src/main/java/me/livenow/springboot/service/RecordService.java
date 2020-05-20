package me.livenow.springboot.service;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.domain.record.Record;
import me.livenow.springboot.domain.record.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final AlarmRepository alarmRepository;

    @Transactional
    public void save(String fileName, String fileDownloadUri, String contentType, long size, String mloName) {
        Record record = new Record();
        record.setFileName(fileName);
        record.setFileDownloadUrl(fileDownloadUri);
        record.setFileType(contentType);
        record.setSize(size);
        record.setLocalDateTime(LocalDateTime.now());

        List<Alarm> allAlarmByRecordTime = alarmRepository.findAllAlarmByRecordTime(mloName, record.getLocalDateTime());
        for(Alarm a : allAlarmByRecordTime)
            record.addAlarm(a);

        recordRepository.save(record);
    }
}
