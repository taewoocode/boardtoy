package com.den.board.controller;

import com.den.board.entity.Board;
import com.den.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost8090에 boardwrite
    public String boardWriteForm() {

        return "boardwrite";

    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model) {

        boardService.write( board );

        model.addAttribute( "message", "글 작성 완료" );
        model.addAttribute( "serachUrl", "/board/list" );

        return "message";

    }

    @GetMapping("/board/list")
    public String boardList(Model model,@PageableDefault(page = 0, size = 10, sort = "id",
    direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.boardList( pageable );

        int nowPage = list.getPageable().getPageNumber() + 1; //page가져오기
        int startPage = Math.max( nowPage - 4, 1 );
        int endPage = Math.min( nowPage + 5, list.getTotalPages() );

        model.addAttribute( "list" );
        model.addAttribute( "nowPage", nowPage );
        model.addAttribute( "endPage", startPage );
        model.addAttribute( "endPage", endPage );
        model.addAttribute( "list" );

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1 -> get방식 parameter로 넘김 출력은 html
    public String boardView(Model model, Integer id) {

        model.addAttribute( "board", boardService.boardView( id ) );
        return "boardview";
    }

    @GetMapping("/board/delete") //게시글을 삭제하면 list로 넘어가게 구현
    public String boardDelete(Integer id) {

        boardService.boardDelete( id );

        return "redirect:/board/list";

    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute( "board", boardService.boardView( id ) );

        return "boardmodify";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception {

        //기존 글 검색 객체
        Board boardTemp = boardService.boardView( id ); //기존
        boardTemp.setTitle( board.getTitle() );
        boardTemp.setContent( board.getContent() );

        boardService.write( boardTemp, file );
        return "redirect:/board/list";
    }
}
