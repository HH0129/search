package com.team.board0326.repository;

import com.team.board0326.entity.SearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
    //제목으로 조회
    Page<SearchEntity> findBySubjectContaining(String subject, Pageable pageable);

    //내용으로 조회
    Page<SearchEntity> findByContentContaining(String content, Pageable pageable);

    //작성자로 조회
    Page<SearchEntity> findByAuthorContaining(String author, Pageable pageable);

    //제목+내용으로 조회
    Page<SearchEntity> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

    //제목+내용+작성자로 조회
    Page<SearchEntity> findBySubjectContainingOrContentContainingOrAuthorContaining(String subject, String content, String author, Pageable pageable);

    //쿼리 사용 시 변수명도 임의사용 가능, 메소드명도 임의지정 가능
    //다중문장처리 시(엔터로 줄바꿈) 각 행마다 "" 범위지정, +로 행을 결함, ""기준으로 위나 아랫줄에 빈공백 1칸 이상!
    @Query("SELECT b FROM SearchEntity b " +
            "WHERE b.subject like %:keyword% or b.content like %:keyword% " +
            "or b.author like %:keyword%")
    Page<SearchEntity> searchALL(String keyword, Pageable pageable);
}
