package com.pltoo.membership.repository;

import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    List<FriendEntity> findByMemberId(Long memberId);
}
