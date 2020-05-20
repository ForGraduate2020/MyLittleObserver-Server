package me.livenow.springboot.domain.record;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class RecordRepository {

    private final EntityManager em;

    public void save(Record record){
        em.persist(record);
    }

    public Record findOne(Long id){
        return em.find(Record.class, id);
    }


}
