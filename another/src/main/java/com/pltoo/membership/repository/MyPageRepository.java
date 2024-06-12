package com.pltoo.membership.repository;

import com.pltoo.membership.entity.MyPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyPageRepository extends JpaRepository<MyPageEntity, String> {
    // 이메일을 기준으로 MyPageEntity를 찾는 메소드 정의
    Optional<MyPageEntity> findByMemberEmail(String memberEmail);
}