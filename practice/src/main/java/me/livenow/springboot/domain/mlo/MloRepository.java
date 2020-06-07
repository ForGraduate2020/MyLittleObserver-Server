package me.livenow.springboot.domain.mlo;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mloUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MloRepository {

    private final EntityManager em;

    public void save (Mlo mlo){
        em.persist(mlo);             //영속성 컨텍스트에 nlo객체를 넣음 transaction이 커밋되는 시점에 디비에 반영됨
    }

    public Mlo findOne(Long id ){
        return em.find(Mlo.class, id);       //jpa의 메서드를 사용, 단건조회기 때문에, 타임 , pk를 넣음
    }

    public List<Mlo> findAll() {
/*        List<Member> result = em.createQuery("select m from Member m ", Member.class)
            .getResultList();

        return result;*/        //이렇게 쿼리를 만들어 줘야하고, result를 클릭후 ctrl + alt + n을 누르면 줄여줌
        // sql는 테이블 대상으로 쿼리하면 jpql는 entity 객체를 통해 함

        return em.createQuery("select m from Mlo m ", Mlo.class)
                .getResultList();
    }

    public List<Mlo> findByName(String name){            // 이름에 의해서 조회하는 기능, 파라미터 바인딩
        return em.createQuery("select m from Mlo m where m.mloName = :mloName", Mlo.class)
                .setParameter("mloName", name)
                .getResultList();
    }

    public Mlo finOnedByName(String name){            // 이름에 의해서 조회하는 기능, 파라미터 바인딩
        try {
            return em.createQuery("select m from Mlo m where m.mloName = :mloName", Mlo.class)
                    .setParameter("mloName", name)
                    .getSingleResult();
        } catch (NoResultException e){
            throw new NoResultException("등록된 MloName이 아닙니다.");
        }
    }

    public List<Mlo> findAlarmByMlo(String name) {
        return em.createQuery("select m from Mlo m"+
                " join fetch m.alarms a"+
                " where m.mloName =:name order by a.date desc ", Mlo.class)
                .setParameter("name", name)
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();
    }
}
