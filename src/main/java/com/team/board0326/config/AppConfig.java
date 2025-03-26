package com.team.board0326.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//환경을 변경
@Configuration
public class AppConfig {
    @Bean   //서버에 지정된 변수 및 메소드를 등록
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

//public : 모든 클래스에서 호출해 사용 가능(외부)
//private : 현재 클래스에서 호출해 사용 가능(내부)