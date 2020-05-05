package me.livenow.springboot.domain.alarm;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import me.livenow.springboot.domain.Record.Record;

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

/*    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="record_id")
    private Record record;*/



}
