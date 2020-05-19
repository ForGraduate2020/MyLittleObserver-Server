package me.livenow.springboot.domain.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {
    private final EntityManager em;

    public void save(Alarm alarm) {
        em.persist(alarm);
    }

    public Alarm findOne(Long id) {
        return em.find(Alarm.class, id);
    }


    public Alarm findRecordByAlarmId(Long id) {
        try {
            findOne(id);
            return em.createQuery("select a from Alarm a" +
                    " join fetch a.record r" +
                    " where a.id= :id", Alarm.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("입력한 Id와 같은 AlarmId가 없습니다.");
        }
    }
}
