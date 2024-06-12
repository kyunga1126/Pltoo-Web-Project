package com.pltoo.membership.repository;

import com.pltoo.membership.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // CRUD를 처리하기 위한 장소이다.
    Page<Board> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}
