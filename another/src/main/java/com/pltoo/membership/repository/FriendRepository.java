package com.pltoo.membership.repository;

import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    // 사용자에 대한 친구 관계 모두 조회
    List<FriendEntity> findByUser(MemberEntity user);
}
