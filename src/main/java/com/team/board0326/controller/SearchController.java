package com.team.board0326.controller;

import com.team.board0326.DTO.CreateDTO;
import com.team.board0326.DTO.DetailDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller   //제어권이 있는 클래스
//@RestController
public class SearchController {

    //목록
    @GetMapping(value = {"/", "/list"}) //메소드명과 연관
                            //괄호 안에 입력인수 등록, 출력값이 있으면 model
    public String listView(@PageableDefault(page=1) Pageable pageable,
                           @RequestParam(value = "type", defaultValue = "") String type,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword,
                           Model model){
        return "search/list";  //String과 연관
    }

    //삽입
    @GetMapping("/create")
    public String createView() {
        return "/search/create";
    }

    @PostMapping("/create")
    public String createProc(CreateDTO createDTO){
        return "redirect:/";
    }

    //삭제
    @GetMapping("/delete")
    public String deleteProc(Integer id)
    {
        return "redirect:/";
    }

    //상세보기
    @GetMapping("/detail")
    public String detailProc(Integer id, Model model){
        return "/search/detail";
    }

    //수정
    @GetMapping("/modify")
    public String modifyView(Integer id, Model model){
        return "/search/modify";
    }

    @PostMapping("/modify")
    public String modifyProc(DetailDTO detailDTO){
        return "redirect:/";
    }

}
