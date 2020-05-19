package me.livenow.springboot.domain.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {
    private final EntityManager em;

    public void save(Alarm alarm){
        em.persist(alarm);}


}
