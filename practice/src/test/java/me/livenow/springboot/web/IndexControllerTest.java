package me.livenow.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void 메인페이지_로딩(){                                                         //url호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트
        //when
        String body = this.testRestTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");                     //index.mustache에 포함된 코드 중 이 문자열이 잇는지 확이만 하면됨.

    }

}
