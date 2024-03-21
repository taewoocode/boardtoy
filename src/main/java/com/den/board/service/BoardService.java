package com.den.board.service;


import com.den.board.entity.Board;
import com.den.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    //글 작성하기
    public void write(Board board) {

        boardRepository.save( board );
    }

    //게시물 리스트 처리
    public List<Board> boardList() {

        return boardRepository.findAll(); //generic list 반환

    }


    //특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById( id ).get(); //Optional
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id) {

        boardRepository.deleteById(  );
    }


}
