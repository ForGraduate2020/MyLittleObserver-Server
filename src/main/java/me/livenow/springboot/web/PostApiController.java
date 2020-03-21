package me.livenow.springboot.web;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.service.posts.PostsService;
import me.livenow.springboot.web.dto.PostsResponseDto;
import me.livenow.springboot.web.dto.PostsSaveRequestDto;
import me.livenow.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostsService postsService;

    @GetMapping("/")
    public String hi(){
        return "안녕?";
    }

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto CheckId (@PathVariable Long id){
        return postsService.findById(id);
    }

}
