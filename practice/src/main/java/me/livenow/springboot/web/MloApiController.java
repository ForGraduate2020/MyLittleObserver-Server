package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.mlo.Mlo;
import me.livenow.springboot.domain.mlo.MloRepository;
import me.livenow.springboot.service.posts.MloService;
import me.livenow.springboot.web.dto.MloSaveRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MloApiController {
    private final MloService mloService;
    private final MloRepository mloRepository;

    @PostMapping("/api/v1/{userId}/mlo")
    public long save(@PathVariable("userId") Long id , @RequestBody @Valid MloSaveRequestDto mloSaveRequestDto){
        return mloService.mloSave(id, mloSaveRequestDto);
    }

}
