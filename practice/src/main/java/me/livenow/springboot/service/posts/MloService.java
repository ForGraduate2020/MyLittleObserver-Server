package me.livenow.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.domain.mloUser.User;
import me.livenow.springboot.domain.mloUser.UserRepository;
import me.livenow.springboot.web.dto.MloSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MloService {
    private final MloRepository mloRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long mloSave(Long id, MloSaveRequestDto mloSaveRequestDto){
        List<Mlo> findMlos = mloRepository.findByName(mloSaveRequestDto.getMloName());
        if(!findMlos.isEmpty()){
            throw new IllegalStateException("이미 사용중인 기기 번호입니다.");
        }
        User user = userRepository.findOne(id);
        Mlo mlo = new Mlo();
        mlo.setMloName(mloSaveRequestDto.getMloName());
        mlo.setUser(user);
        mloRepository.save(mlo);
        return mlo.getId();


    }

}
