package me.livenow.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.livenow.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {             //만든시간 , 수정시간을 넣기위해 상속받는다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; //column을 굳이 선언하지 않아도 해당 클래스의 모든 필드는 column이 됨.

    @Builder
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
