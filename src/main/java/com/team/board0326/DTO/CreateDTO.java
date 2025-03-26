package com.team.board0326.DTO;

import lombok.*;

import java.time.LocalDateTime;

//등록 DTO

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class CreateDTO {
    private String subject;         //제목
    private String content;         //내용
    private String author;          //작성자
}
