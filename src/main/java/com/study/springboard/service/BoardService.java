package com.study.springboard.service;

import com.study.springboard.entity.springboard;
import com.study.springboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 작성 버튼 누르면 데이터가 DB에 저장 + 수정 후 새로운 데이터도 저장
    public void boardWrite(springboard springboard) {

        boardRepository.save(springboard);
    }

    // springboard 클래스가 담긴 list 반환
    public List<springboard> boardList() {
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기 + 수정에서 기존 데이터 불러올 때도 사용
    public springboard boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
