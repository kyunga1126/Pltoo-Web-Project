package com.pltoo.membership.dto;

import com.pltoo.membership.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDTO {
    private Long memberNum;
    private String nickname;

    public static FriendDTO fromEntity(MemberEntity member) {
        FriendDTO dto = new FriendDTO();
        dto.setMemberNum(member.getMemberNum()); // Memberentity의 id를 DTO id 필드에 설정
        dto.setNickname(member.getNickname()); // MemberEntity의 닉네임을 DTO 닉네임에 설정
        return dto;
    }
}
