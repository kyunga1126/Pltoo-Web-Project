package com.pltoo.membership.entity;

import com.pltoo.membership.dto.MyPageDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
@Setter
@Entity
@Table(name = "myprofile") // 마이페이지와 DB 공유
public class MyPageEntity {
    private static final Logger log = LoggerFactory.getLogger(MyPageEntity.class);

    @Id
    @Column(name = "email", nullable = false)
    private String memberEmail;

    @Column(name = "profile_img", length = 50) // 프로필 이미지
    private String profileImage;

    @Column(name = "profile", length = 50) // 자기소개
    private String profile;

    @Column(name = "age")
    private String age;

    @Column(name = "friend_num")
    private Integer friendNum;

    @Column(name = "friend_list")
    private String friendList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 컬럼 이름을 'member_id'로 명확하게 지정
    private MemberEntity memberEntity; // MemberEntity와의 관계 설정

    public static MyPageEntity saveMyPageEntity(MyPageDTO myPageDTO, MemberEntity member) {
        MyPageEntity myPageEntity = new MyPageEntity();
        myPageEntity.setProfileImage(myPageDTO.getProfileImage());
        myPageEntity.setProfile(myPageDTO.getProfile());
        myPageEntity.setFriendNum(myPageDTO.getFriendNum());
        myPageEntity.setFriendList(myPageDTO.getFriendList());
        myPageEntity.setMemberEntity(member);
        return myPageEntity;
    }

    public static MyPageEntity updateMyPageEntity(MyPageEntity entity, MyPageDTO dto) {
        entity.setProfileImage(dto.getProfileImage());
        entity.setProfile(dto.getProfile());
        entity.setFriendNum(dto.getFriendNum());
        entity.setFriendList(dto.getFriendList());
        return entity;
    }

}
