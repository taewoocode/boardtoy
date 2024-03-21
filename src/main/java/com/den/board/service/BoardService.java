package com.den.board.service;


import com.den.board.entity.Board;
import com.den.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    //글 작성하기
    public void write(Board board, MultipartFile file) throws Exception {

        String projectPath = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File( projectPath, fileName );

        file.transferTo( saveFile );

        board.setFilename( fileName );
        board.setFilepath( "/files/" + fileName );

        board.setFilename( fileName );
        board.setFilepath( "/files/" + fileName  );
        boardRepository.save( board );
    }

    //게시물 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll( pageable );
    }

    public Page<Board> boardSearchList(String serachKeyword, Pageable pageable) {
        return boardRepository
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }


}
