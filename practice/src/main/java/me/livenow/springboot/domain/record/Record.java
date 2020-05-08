package me.livenow.springboot.domain.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.alarm.Alarm;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Record {
    @Id @GeneratedValue
    @Column(name="record_id")
    private Long id;

    private String fileName;

/*    @OneToOne(mappedBy = "record",fetch = FetchType.LAZY)
    private Alarm alarm;*/

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;


    @Lob
    @Column(length=1024000)
    private byte[] data;



    public void setAlarm(Alarm alarm){
        this.alarm= alarm;
        alarm.setRecord(this);
    }


}
