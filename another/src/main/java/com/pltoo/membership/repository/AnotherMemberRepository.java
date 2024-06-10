package com.pltoo.membership.repository;

import com.pltoo.membership.entity.AnotherMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotherMemberRepository extends JpaRepository<AnotherMemberEntity, Long> {

}
