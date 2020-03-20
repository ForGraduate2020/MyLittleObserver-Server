package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.service.posts.PostsService;
import me.livenow.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostsService postsService;

    @PutMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

}
