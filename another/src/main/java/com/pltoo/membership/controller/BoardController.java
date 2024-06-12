package com.pltoo.membership.controller;


import com.pltoo.membership.Service.BoardService;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.Board;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 글쓰기 페이지
    @GetMapping("/board/write")
    public String boardwrite() {

        return "html/boardWrite";
    }

    // 글쓰기 완료 페이지
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {

//        System.out.println(board.getId());
//        System.out.println(board.getNickname());
//        System.out.println(board.getBoardTitle());
//        System.out.println(board.getBoardContent());
        boardService.boardwrite(board, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "html/message";
    }

    // 게시판 목록 페이지
    @GetMapping("/board/list")
    public String boardlist(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session,
                            @PageableDefault(page = 0, size = 10, sort = "boardNum", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) throws Exception {

        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("email", loginEmail);
        log.info("BoardLogin :" + loginEmail);

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }


        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 4, list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "html/boardList";
    }

    // 게시판 상세보기 페이지
    @GetMapping("/board/view") // localhost:8080/board/view?boardNum=1
    public String boardview(Model model, Integer boardNum) {

        model.addAttribute("board", boardService.boardView(boardNum));

        return "html/boardView";
    }

    // 게시판 삭제 페이지
//    @PostMapping("/board/delete/{boardNum}")
//    public String boardDelete(@PathVariable("boardNum") Integer boardNum) {
//        boardService.boardDelete(boardNum);
//        return "redirect:/board/list";
//    }
    @GetMapping("/board/delete/{boardNum}")
    public String boardDelete(@PathVariable("boardNum") Integer boardNum) {
        boardService.boardDelete(boardNum);
        return "redirect:/board/list";
    }

    // 게시판 수정 페이지
    @GetMapping("/board/modify/{boardNum}")
    public String boardModify(@PathVariable("boardNum") Integer boardNum, Model model) {

        model.addAttribute("board", boardService.boardView(boardNum));

        return "html/boardmodify";
    }


    // 게시판 수정 처리 페이지
    @PostMapping("/board/update/{boardNum}")
    public String boardUpdate(@PathVariable("boardNum") Integer boardNum, Board board, Model model, MultipartFile file) throws Exception {

        Board boardTemp = boardService.boardView(boardNum);
        boardTemp.setBoardTitle(board.getBoardTitle());
        boardTemp.setBoardContent(board.getBoardContent());
        boardTemp.setNickname(board.getNickname());
        boardTemp.setId(board.getId());

        boardService.boardwrite(boardTemp, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "html/message";
    }




}
