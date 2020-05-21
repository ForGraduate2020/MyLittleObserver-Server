package me.livenow.springboot.domain.mloUser;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save (User user){
        em.persist(user);             //영속성 컨텍스트에 nlo객체를 넣음 transaction이 커밋되는 시점에 디비에 반영됨
    }

    public User findOne(Long id ){
        return em.find(User.class, id);       //jpa의 메서드를 사용, 단건조회기 때문에, 타임 , pk를 넣음
    }

    public List<User> findAll() {
/*        List<Member> result = em.createQuery("select m from Member m ", Member.class)
            .getResultList();

        return result;*/        //이렇게 쿼리를 만들어 줘야하고, result를 클릭후 ctrl + alt + n을 누르면 줄여줌
        // sql는 테이블 대상으로 쿼리하면 jpql는 entity 객체를 통해 함

        return em.createQuery("select u from User u ", User.class)
                .getResultList();
    }

    public List<User> findByName(String name){            // 이름에 의해서 조회하는 기능, 파라미터 바인딩
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }


    public List<User> findMloByName(String name, int offset, int limit){            // 이름에 의해서 조회하는 기능, 파라미터 바인딩
            if(findByName(name).isEmpty())
                throw new NullPointerException("등록되지 않은 사용자입니다.");

            return em.createQuery("select u from User u " +
                    " join fetch u.mlos m" +
                    " where u.name = :name", User.class)
                    .setParameter("name", name)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
    }

    public List<User> findAllWithMlos(int offset, int limit) {
        return em.createQuery(
                "select u from User u"+
                        " join fetch u.mlos", User.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
