package me.livenow.springboot.domain.alarm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.record.Record;
import me.livenow.springboot.domain.mlo.Mlo;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mlo_id")
    private Mlo mlo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    //==연관관계 편의 메서드 ==//
    public void setMlo(Mlo mlo){
        this.mlo = mlo;
        mlo.getAlarms().add(this);
    }


}
