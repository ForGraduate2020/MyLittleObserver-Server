package me.livenow.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass                                       //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하게함.
@EntityListeners(AuditingEntityListener.class)          //BaseTimeEntity(현재클래스)에서 Auditing 기능을 포함.

public class BaseTimeEntity {

    @CreatedDate                            //Entity가 생성되어 저장될 때 시간이 자동 저장됨.
    private LocalDateTime createdDate;

    @LastModifiedDate                       //조회환 Entity의 값을 변경할 때 시간이 자동 저장됨.
    private LocalDateTime modifiedDate;

}
