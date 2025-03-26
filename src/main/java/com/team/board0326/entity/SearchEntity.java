package com.team.board0326.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//lombok을 이용해 메소드를 자동화(생성자 일일이 추가할 필요없게)
//테이블 조인 작업 시 반드시 @toString은 삭제. 사용하면 안 된다.
//toString 사용 시 재귀함수로 무한반복 오류의 굴레에 빠지게 된다... 조심할것!
@Getter @Setter @ToString @Builder
@AllArgsConstructor @ NoArgsConstructor

@Entity
@SequenceGenerator( //자동숫자를 생성하고 정보를 저장
        name="board0326_seq", //테이블명+seq
        sequenceName = "board0326_seq",
        initialValue = 1, //시작값
        allocationSize = 1  //크기
)   //Entity와 1:1로만 사용
@EntityListeners(AuditingEntityListener.class)  //createdDate, LastModifyDate 불가능
public class SearchEntity {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board0326_seq")   //기본키의 사용방법, 순차처리(1,2,3) 불규칙하게 숫자생성
    @Column(name = "id", nullable = false) //name=필드명, nullable=생략, length=길이 => 생략하면 변수명으로 자동생성
    private Integer id;         //번호

    @Column(name = "subject", nullable = false, length = 50)
    private String subject;     //제목

    @Column(name = "content", length = 200)
    private String content;     //내용

    @Column(name = "author", length = 20)
    private String author;      //작성자

    @CreatedDate    //생성 시 자동생성
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;     //등록일자

    @LastModifiedDate   //최근(최종)수정 시 자동생성
    @Column(name = "modDate")
    private LocalDateTime modDate;     //수정일자

    //생성자, Getter, Setter, toString, Build 메소드


    //modelmapper를 사용하지 않는다면 이곳에 변환 메소드
}

//1. 변수선언
//2. Column 지정
//3. Join 지정