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
import java.time.LocalDateTime;


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
       // initService.dbInit1();
        initService.dbInitForEc2();
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


            Record record =new Record();
            record.setFileName("file1");
            record.setFileDownloadUrl("http://localhost:8080/downloadFile/file1.mp3");
            record.setFileType("audio/mpeg");
            record.setSize(5811859);
            record.setLocalDateTime(LocalDateTime.now());
            em.persist(record);

            Alarm alarm = new Alarm();
            alarm.setMlo(mlo);
            alarm.setTumble("9");
            alarm.setDate(LocalDateTime.now());
            alarm.setRecord(record);
            em.persist(alarm);


            Alarm alarm2 = new Alarm();
            alarm2.setMlo(mlo);
            alarm2.setDecibel("9");
            alarm2.setTumble("2");
            alarm2.setDate(LocalDateTime.now());
            alarm2.setRecord(record);
            em.persist(alarm2);

        }
        public void dbInitForEc2(){
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


            Record record =new Record();
            record.setFileName("file1");
            record.setFileDownloadUrl("http://ec2-15-165-113-25.ap-northeast-2.compute.amazonaws.com:8080/downloadFile/file1.mp3");
            record.setFileType("audio/mpeg");
            record.setSize(5811859);
            record.setLocalDateTime(LocalDateTime.now());
            em.persist(record);

            Alarm alarm = new Alarm();
            alarm.setMlo(mlo);
            alarm.setTumble("9");
            alarm.setDate(LocalDateTime.now());
            alarm.setRecord(record);
            em.persist(alarm);


            Alarm alarm2 = new Alarm();
            alarm2.setMlo(mlo);
            alarm2.setDecibel("9");
            alarm2.setTumble("2");
            alarm2.setDate(LocalDateTime.now());
            alarm2.setRecord(record);
            em.persist(alarm2);

        }
    }
}
