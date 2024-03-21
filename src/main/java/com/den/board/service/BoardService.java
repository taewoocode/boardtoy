package com.den.board.service;


import com.den.board.entity.Board;
import com.den.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {

        boardRepository.save(board);
    }
}
