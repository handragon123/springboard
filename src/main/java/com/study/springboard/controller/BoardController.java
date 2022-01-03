package com.study.springboard.controller;

import com.study.springboard.entity.springboard;
import com.study.springboard.service.BoardService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성폼 페이지
    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }

    // 작성 버튼 눌렀을 때 처리
    @PostMapping("/board/writepro")
    public String boardWritePro(springboard springboard, Model model) {
        boardService.boardWrite(springboard);
        //model.addAttribute("list", boardService.boardList());
        return "redirect:/board/list";
    }

    // 게시글 리스트 페이지 - DB의 데이터를 페이지로 보내기(Model)
    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardList());

        return "boardlist";
    }

    // 게시글 상세 페이지 - 리스트에서 게시글 눌렀을 때 해당 게시글의 상세 페이지로 이동
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardview";
    }

    // 게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    // 게시글 수정 페이지 보여주기
    @GetMapping("/board/modify/{id}")
    public String boardModify(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";

    }

    // 게시글 수정버튼 누르면 DB에 데이터 저장
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, springboard springboard) {

        springboard boardTemp = boardService.boardView(id);
        boardTemp.setTitle(springboard.getTitle());
        boardTemp.setContent(springboard.getContent());

        boardService.boardWrite(boardTemp);

        return "redirect:/board/list";
    }

}
