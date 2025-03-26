package com.team.board0326.controller;

import com.team.board0326.DTO.CreateDTO;
import com.team.board0326.DTO.DetailDTO;
import com.team.board0326.DTO.ListDTO;
import com.team.board0326.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.team.board0326.util.PagenationUtil.Pagination;

@Controller   //제어권이 있는 클래스
@RequiredArgsConstructor
//@RestController
public class SearchController {
    //클래스 연동
    private final SearchService searchService;

    //목록
    @GetMapping(value = {"/", "/list"}) //메소드명과 연관
                            //괄호 안에 입력인수 등록, 출력값이 있으면 model
    public String listView(@PageableDefault(page=1) Pageable pageable,
                           @RequestParam(value = "type", defaultValue = "") String type,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword,
                           Model model){
        //서비스 연동
        Page<ListDTO> listDTOS = searchService.list(pageable, type, keyword);

        //페이지정보 가공
        Map<String, Integer> pageInfo = Pagination(listDTOS);

        //값 전달 (Model)
        model.addAttribute("list", listDTOS);
        //조회정보전달
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        //페이지정보전달
        model.addAllAttributes(pageInfo);

        return "search/list";  //String과 연관
    }

    //삽입(h2-console)
    @GetMapping("/create")
    public String createView() {
        return "/search/create";
    }

    @PostMapping("/create")
    public String createProc(CreateDTO createDTO){
        //서비스를 통해 내부처리
        searchService.create(createDTO);

        return "redirect:/";
    }

    //삭제
    @GetMapping("/delete")
    public String deleteProc(Integer id) {
        //서비스 처리
        searchService.delete(id);

        return "redirect:/";
    }

    //상세보기
    @GetMapping("/detail")
    public String detailProc(Integer id, Model model){
        //서비스 처리
        DetailDTO detailDTO = searchService.detail(id);

        model.addAttribute("data", detailDTO);
        return "/search/detail";
    }

    //수정
    @GetMapping("/modify")
    public String modifyView(Integer id, Model model){
        //서비스 처리(수정폼에 기존 내용을 출력)
        DetailDTO detailDTO = searchService.detail(id);

        model.addAttribute("data", detailDTO);
        return "/search/modify";
    }

    @PostMapping("/modify")
    public String modifyProc(DetailDTO detailDTO){
        //서비스 처리(수정된 내용 저장)
        searchService.modify(detailDTO);

        return "redirect:/";
    }

}
