package com.pltoo.membership.repository;

import com.pltoo.membership.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
//Entity이름과 Entity PK컬럼의 타입
//DB를 넘길때 반드시 Entity객체로 넘겨야 한다.
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원정보 조회(select * from membership_table where member_email=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    Optional<MemberEntity> findById(Long id);

    boolean existsByMemberEmail(String memberEmail);

    @Query("SELECT m.memberNum FROM MemberEntity m WHERE m.memberEmail = :email")
    Long findIdByEmail(@Param("email") String email);

}
