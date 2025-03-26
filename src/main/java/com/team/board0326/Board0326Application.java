package com.team.board0326;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//스프링부트 실행
@SpringBootApplication
//JPA감사 활성화(날짜 자동생성)
@EnableJpaAuditing
public class Board0326Application {

    public static void main(String[] args) {
        SpringApplication.run(Board0326Application.class, args);
    }

}
