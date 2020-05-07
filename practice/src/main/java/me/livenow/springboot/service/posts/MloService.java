package me.livenow.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.Record.RecordRepository;
import me.livenow.springboot.domain.alarm.AlarmRepository;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.domain.mloUser.User;

import me.livenow.springboot.domain.mloUser.UserRepository;
import me.livenow.springboot.web.dto.UserSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MloService {
    private final AlarmRepository alarmRepository;
    private final MloRepository mloRepository;
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long userSave(UserSaveRequestDto saveRequestDto){
        List<User> findUsers = userRepository.findByName(saveRequestDto.getUsername());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        User user = new User();
        user.setName(saveRequestDto.getUsername());
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findMlo(){
        return userRepository.findMlo();
    }



}
