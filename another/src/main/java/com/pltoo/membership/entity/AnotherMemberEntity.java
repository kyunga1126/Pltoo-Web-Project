package com.pltoo.membership.entity;

import com.pltoo.membership.dto.GameDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "myprofile") //마이페이지와 db 공유
public class AnotherMemberEntity {

    @Id //MemberEntity의 id 가져오기
    @Column(name = "email", length = 50, unique = true) // 저장이 아닌 업데이트update가 되게끔.
    private String memberEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)  // 변경된 부분
    private MemberEntity memberNum;

    @Column(name = "profile_img", length = 50) // 프로필 이미지
    private String profileImage;

    @Column(name = "profile", length = 50) //자기소개
    private String profile;

    @Column(name = "friend_num")
    private Integer friendNum;

    public static AnotherMemberEntity toAnotherMemberEntity(MemberEntity member) {
        AnotherMemberEntity anotherMemberEntity = new AnotherMemberEntity();
        anotherMemberEntity.setMemberNum(member);
        anotherMemberEntity.setMemberEmail(member.getMemberEmail());
        return anotherMemberEntity;
    }

}
