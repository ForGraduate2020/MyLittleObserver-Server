package me.livenow.springboot;


import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.alarm.Alarm;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mloUser.User;
import me.livenow.springboot.domain.record.Record;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


/**
 * UserA
 * mlo1, tumble1 , record1
 * mlo2, tumble2 , record2
 */

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;


    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            User user = new User();
            user.setName("userA");
            em.persist(user);

            Mlo mlo = new Mlo();
            mlo.setMloName("mlo1");
            mlo.setUser(user);
            em.persist(mlo);

            Mlo mlo2 = new Mlo();
            mlo2.setMloName("mlo2");
            mlo2.setUser(user);
            em.persist(mlo);

            Alarm alarm = new Alarm();
            alarm.setMlo(mlo);
            alarm.setTumble("tumble1");
            em.persist(alarm);


            Record record =new Record();
            record.setAlarm(alarm);
            record.setFileName("record1");
            em.persist(record);

            Alarm alarm2 = new Alarm();
            alarm2.setMlo(mlo);
            alarm2.setTumble("tumble2");
            em.persist(alarm2);


            Record record2 =new Record();
            record2.setAlarm(alarm2);
            record2.setFileName("record2");
            em.persist(record2);


        /*    Alarm alarm1=Alarm.createAlarm(mlo,null);       //녹음 값이 없을때

            em.persist(alarm1);

            Record record = new Record();
            record.setFileName("record1");

            Alarm alarm2= Alarm.createAlarm(mlo, record);           //녹음된 값이 있을때
            em.persist(alarm2);*/


        }
        public void dbInit2() {
            User user = new User();
            user.setName("userB");
            em.persist(user);

            Mlo mlo = new Mlo();
            mlo.setMloName("mlo3");
            mlo.setUser(user);
            em.persist(mlo);

            Mlo mlo2 = new Mlo();
            mlo2.setMloName("mlo4");
            mlo2.setUser(user);
            em.persist(mlo);

            Alarm alarm = new Alarm();
            alarm.setMlo(mlo);
            alarm.setTumble("tumble3");
            em.persist(alarm);


            Record record = new Record();
            record.setAlarm(alarm);
            record.setFileName("record3");
            em.persist(record);

            Alarm alarm2 = new Alarm();
            alarm2.setMlo(mlo);
            alarm2.setTumble("tumble4");
            em.persist(alarm2);


            Record record2 = new Record();
            record2.setAlarm(alarm2);
            record2.setFileName("record4");
            em.persist(record2);
        }

        }

}
