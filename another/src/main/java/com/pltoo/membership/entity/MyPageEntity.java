package com.pltoo.membership.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "myprofile")
public class MyPageEntity {

    @Id //MemberEntity의 id 가져오기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)  // 변경된 부분
    private MemberEntity memberNum;

    @OneToMany(mappedBy = "member")
    private List<FriendEntity> friends;

    private String profileImage; // 프로필 이미지 파일의 경로를 저장하는 필드

    public static AnotherMemberEntity toAnotherMemberEntity(MemberEntity member) {
        AnotherMemberEntity anotherMemberEntity = new AnotherMemberEntity();
        anotherMemberEntity.setMemberNum(member);
        anotherMemberEntity.setMemberEmail(member.getMemberEmail());
        return anotherMemberEntity;
    }

}

