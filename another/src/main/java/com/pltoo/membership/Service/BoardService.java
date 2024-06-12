package com.pltoo.membership.Service;


import com.pltoo.membership.entity.Board;
import com.pltoo.membership.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    public BoardRepository boardRepository;

    // 글작성 처리 / 글수정 처리
    public void boardwrite(Board board, MultipartFile file) throws Exception {

        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String filename = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, filename);

        file.transferTo(saveFile);

        board.setFilename(filename);
        board.setFilepath("/files/" + filename);

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) throws Exception {

        return boardRepository.findAll(pageable);
    }

    // 게시글 검색
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) throws Exception {

        return boardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer boardNum) {


        return boardRepository.findById(boardNum).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer boardNum) {

        boardRepository.deleteById(boardNum);
    }
}
