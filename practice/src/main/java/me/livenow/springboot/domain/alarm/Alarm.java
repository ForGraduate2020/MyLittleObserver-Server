package me.livenow.springboot.domain.alarm;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.record.Record;
import me.livenow.springboot.domain.mlo.Mlo;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alarm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    private String heart;
    private String decibel;
    private String tumble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mlo_id")
    private Mlo mlo;

    @OneToOne(mappedBy = "alarm", fetch = FetchType.LAZY)
    private Record record;

    //==연관관계 편의 메서드 ==//
    public void setMlo(Mlo mlo){
        this.mlo = mlo;
        mlo.getAlarms().add(this);
    }

/*    public void setRecord(Record record){
        this.record = record;
        record.setAlarm(this);
    }

    //== 생성 메서드 ==//
    public static Alarm createAlarm(Mlo mlo, Record record){
        Alarm alarm = new Alarm();
        alarm.setMlo(mlo);
        alarm.setRecord(record);
        return alarm;
    }*/



}
