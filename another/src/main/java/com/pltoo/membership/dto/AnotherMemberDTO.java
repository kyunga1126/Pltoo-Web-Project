package com.pltoo.membership.dto;

import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnotherMemberDTO {
    private Long memberNum;
    private String nickname;
    private String age;
    private String profile;
    private String profileImage;

    public static AnotherMemberDTO fromEntity(MemberEntity member, MyPageEntity mypage) {
        AnotherMemberDTO dto = new AnotherMemberDTO();
        dto.setMemberNum(member.getMemberNum());
        dto.setNickname(member.getNickname());
        dto.setAge(mypage.getAge());
        dto.setProfile(mypage.getProfile());
        dto.setProfileImage(mypage.getProfileImage());
        return dto;
    }
}
