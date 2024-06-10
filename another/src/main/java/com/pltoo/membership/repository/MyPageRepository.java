package com.pltoo.membership.repository;

import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<MyPageEntity, Long> {
    MemberEntity findByEmail(String email);
}
