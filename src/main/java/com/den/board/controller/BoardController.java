package com.den.board.controller;

import com.den.board.entity.Board;
import com.den.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost8090에 boardwrite
    public String boardWriteForm() {

        return "boardwrite";

    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {

        boardService.write( board );

        return "";

    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute( "list", boardService.boardList() );

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardView() {

        return "boardview";
    }
}
