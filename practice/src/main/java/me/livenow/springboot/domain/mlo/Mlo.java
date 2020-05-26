package me.livenow.springboot.domain.mlo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.mloUser.User;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mlo {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="mlo_id")
    private Long id;

    @Column(nullable = false)
    private String mloName;         //mloId라고하면 안됫었음, Id를 변수로 넣는거 조심하자.

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "mlo",  cascade = CascadeType.ALL)
    private List<Alarm> alarms = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user){
        this.user = user;
        user.getMlos().add(this);
    }

    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
        alarm.setMlo(this);
    }
}
