package com.team.board0326.service;

import com.team.board0326.DTO.CreateDTO;
import com.team.board0326.DTO.DetailDTO;
import com.team.board0326.DTO.ListDTO;
import com.team.board0326.entity.SearchEntity;
import com.team.board0326.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {

        //연계 클래스
        private final SearchRepository searchRepository;
        private final ModelMapper modelMapper;

        //컨트롤러의 메소드의 () 안 인수를 보고 작성한다
        //목록
        public Page<ListDTO> list(Pageable pageable, String type, String keyword){
            //페이지정보
            int currentPage = pageable.getPageNumber()-1;
            int limits = 10;
            Pageable temp = PageRequest.of(currentPage, limits, Sort.by(Sort.Direction.DESC, "id"));

            //todo : 조건 별 조회 추가
            //2가지 구분(조건대상), 검색어
            //검색이 여러개이면 저장변수를 먼저 분리
            Page<SearchEntity> searchEntities;
            //구분값이 있으면 if문으로 (switch) 분리
            //문자열 비교 시 ==(등호2개)는 부정확함. equals로 대체한다.
            System.out.println("타입"+type);
            System.out.println("키워드"+keyword);
            if(type.equals("S") && keyword != null){//구분이 제목이고, 검색어가 있으면
                System.out.println("dkdkdk");
            searchEntities = searchRepository.findBySubjectContaining(keyword, temp);
            System.out.println(searchEntities.toString());
        }else if(type.equals("C") && keyword != null){  //구분이 내용이고, 검색어가 있으면
            searchEntities = searchRepository.findByContentContaining(keyword, temp);
        }else if(type.equals("A") && keyword != null) {  //구분이 작성자고, 검색어가 있으면
            searchEntities = searchRepository.findByAuthorContaining(keyword, temp);
        }else if(type.equals("SC") && keyword != null) {  //구분이 제목+내용이고, 검색어가 있으면
            searchEntities = searchRepository.findBySubjectContainingOrContentContaining(keyword, keyword, temp);
        }else if(type.equals("SCA") && keyword != null) {  //구분이 제목+내용+작성자고, 검색어가 있으면
            searchEntities = searchRepository.findBySubjectContainingOrContentContainingOrAuthorContaining(keyword, keyword, keyword, temp);
        }else { //해당사항이 없으면
            //전체조회
            searchEntities = searchRepository.findAll(temp);
        }

        //변환
        Page<ListDTO> listDTOS = searchEntities.map(data->modelMapper.map(data, ListDTO.class));

        return listDTOS;
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
        //SQL 처리
        searchRepository.deleteById(id);
    }

    //상세보기
    public DetailDTO detail(Integer id){
        //SQL 처리
        Optional<SearchEntity> searchEntity = searchRepository.findById(id);

        //출력
        DetailDTO detailDTO = modelMapper.map(searchEntity, DetailDTO.class);

        return detailDTO;
    }

    //수정
    public void modify(DetailDTO detailDTO){
        //기존데이터를 조회해서
        Optional<SearchEntity> temp = searchRepository.findById(detailDTO.getId());

        //변환
        SearchEntity searchEntity = modelMapper.map(detailDTO, SearchEntity.class);
        //기존의 등록날짜에 부족한 내용을 추가
        searchEntity.setRegDate(temp.get().getRegDate());

        //SQL 처리
        searchRepository.save(searchEntity);
    }
}


