package me.livenow.springboot.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.alarm.Alarm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Record {

    @Id @GeneratedValue
    @Column(name="record_id")
    private Long id;

    private LocalDateTime localDateTime;
    private String fileName;
    private String fileDownloadUrl;
    private String fileType;
    private long size;

    @OneToMany(mappedBy = "record",cascade = CascadeType.ALL)
    private List<Alarm> alarms = new ArrayList<>();

    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
        alarm.setRecord(this);


    }


}
