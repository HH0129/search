package com.team.board0326.service;

import com.team.board0326.DTO.CreateDTO;
import com.team.board0326.DTO.DetailDTO;
import com.team.board0326.DTO.ListDTO;
import com.team.board0326.entity.SearchEntity;
import com.team.board0326.repository.SearchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


//주석 요령, 설명
//Todo: 앞으로 작업할 지시내용
//FixMe: 수정할 부분에 지시내용

@Service    //문제해결 영역설정
@Transactional  //일괄처리(생략가능)
@RequiredArgsConstructor
public class SeachService {
    //연계 클래스
    private final SearchRepository searchRepository;
    private final ModelMapper modelMapper;

    //컨트롤러의 메소드의 () 안 인수를 보고 작성한다
    //목록
    public Page<ListDTO> list(Pageable pageable, String type, String keyword){
        return null;
    }

    //삽입
    public void create(CreateDTO createDTO){
        //변환(DTO->Entity) 외부데이터를 내부용으로 변환(준비)
        SearchEntity searchEntity = modelMapper.map(createDTO, SearchEntity.class);
        //SQL구동(처리)
        searchRepository.save(searchEntity);
        //(출력)-void라서 리턴 필요없음
    }

    //삭제
    public void delete(Integer id){

    }

    //상세보기
    public DetailDTO detail(Integer id){
        return null;
    }

    //수정
    public void modify(DetailDTO detailDTO){

    }
}
