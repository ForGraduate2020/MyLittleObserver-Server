package me.livenow.springboot.domain.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.alarm.Alarm;

import javax.persistence.*;
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

    private String fileName;

/*    @OneToOne(mappedBy = "record",fetch = FetchType.LAZY)
    private Alarm alarm;*/

    @OneToMany(mappedBy = "record",cascade = CascadeType.ALL)
    private List<Alarm> alarms = new ArrayList<>();

    @Lob
    @Column(length=1024000)
    private byte[] data;
}
