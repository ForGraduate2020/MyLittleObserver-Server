package me.livenow.springboot.domain.record;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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


    public Record findByName(String name) {            // 이름에 의해서 조회하는 기능, 파라미터 바인딩
        return em.createQuery("select r from Record r where r.fileName = :name", Record.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    public List<Record> findAllByFileName(String fileName) {
        return em.createQuery("select r from Record r" +
                              //  " join fetch r.alarms a"+
                                " where r.fileName =:fileName", Record.class)
                .setParameter("fileName", fileName)
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();
    }
}
