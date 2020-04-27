package me.livenow.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public Posts toEntity(){                                    //Entity  클래스를 Request/Response 클래스로 사용하면 절대 안됨.
        return Posts.builder()                                  //Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기 때문임.
                .title(title)                                   //화면 변경을 위해 테이블에 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경이다. 그렇기 때문에 toEntity라는것을 새로 만듬
                .content(content)
                .author(author)
                .build();

    }

}
