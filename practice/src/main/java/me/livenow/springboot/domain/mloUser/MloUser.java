package me.livenow.springboot.domain.mloUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.livenow.springboot.domain.mlo.Mlo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MloUser {
    @Id @GeneratedValue
    @Column(name = "mlouser_id")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="mlouser_id")
    private List<Mlo> mlos = new ArrayList<>();

}
