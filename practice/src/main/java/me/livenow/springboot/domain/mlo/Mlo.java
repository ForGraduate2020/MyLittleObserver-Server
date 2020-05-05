package me.livenow.springboot.domain.mlo;

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
public class Mlo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="mlo_id")
    private Long id;

    @Column(nullable = false)
    private String mloName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="mlo_id")
    private List<Alarm> alarms = new ArrayList<>();




}
