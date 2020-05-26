package me.livenow.springboot.domain.alarm;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {

    private final EntityManager em;
    private final MloRepository mloRepository;

    public void save(Alarm alarm) {
        em.persist(alarm);
    }

    public Alarm findOne(Long id) {
        return em.find(Alarm.class, id);
    }

    public Alarm findRecordByAlarmId(Long id) {
            if(findOne(id)==null)
                throw new NoResultException("등록된 alarmId가 없습니다.");

            try {
                return em.createQuery("select a from Alarm a" +
                        " join fetch a.record r" +
                        " where a.id= :id", Alarm.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e){
                throw new NoResultException( "입력한 Id에 등록된 file이 없습니다. ");
            }
    }

    public List<Alarm> findAllAlarmByRecordTime(String mloName, LocalDateTime localDateTime) {

        mloRepository.finOnedByName(mloName);
        return em.createQuery("select a from Alarm a" +
                " where a.date <= :localDateTime and a.record is NULL and a.mlo.mloName =:mloName ")
                .setParameter("localDateTime", localDateTime)
                .setParameter("mloName", mloName)
                .getResultList();
    }
}
