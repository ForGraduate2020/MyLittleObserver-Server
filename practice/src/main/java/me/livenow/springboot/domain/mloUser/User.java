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
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Mlo> mlos = new ArrayList<>();


}
