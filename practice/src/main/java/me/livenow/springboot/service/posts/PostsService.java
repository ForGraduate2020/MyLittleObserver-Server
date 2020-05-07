/*
package me.livenow.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import me.livenow.springboot.domain.posts.Posts;
import me.livenow.springboot.domain.posts.PostsRepository;
import me.livenow.springboot.web.dto.PostsListResponseDto;
import me.livenow.springboot.web.dto.PostsResponseDto;
import me.livenow.springboot.web.dto.PostsSaveRequestDto;
import me.livenow.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto saveRequestDto){
        return postsRepository.save(saveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자ㅏ 업습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());                   // 원래있던 쿼리를 날릴 필요가 없는 이유는 JPA의 영속성 컨텍스트 때문임(엔티티를 영구 저장하는 환경)
        return id;                                                                      // 따라서 jpa의 엔티티 메니저가 활성화된 상태(spring data jpa를 쓴다면 기본)에서  트렌젝션 안에서 데이터베이스의 데이터를 가져와서
                                                                                        // 이 데이터를 변경하면 트렌젝션이 끝나는 시점에 해당 테이블에 변경분을 반영하기 때문
    }
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        postsRepository.delete(posts);
    }
    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자ㅏ 업습니다. id="+id));

        return new PostsResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }




}
*/
