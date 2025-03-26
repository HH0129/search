package com.team.board0326.DTO;

//상세보기 및 수정 dto

import lombok.*;

import java.time.LocalDateTime;

//상세보기 및 수정 DTO

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailDTO {
    private Integer id;             //일련번호
    private String subject;         //제목
    private String content;         //내용
    private String author;          //작성자
    private LocalDateTime modDate;  //수정일자
}
