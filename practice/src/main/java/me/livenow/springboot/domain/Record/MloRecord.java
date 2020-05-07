package me.livenow.springboot.domain.Record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MloRecord {
    @Id @GeneratedValue
    @Column(name="record_id")
    private Long id;

    private String fileName;

    @Lob
    @Column(length=1024000)
    private byte[] data;

}
