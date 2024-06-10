package com.pltoo.membership.dto;

import com.pltoo.membership.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
@Setter
//@NoArgsConstructor //기본생성자를 자동으로 만들어준다
@ToString
// DTO는 회원정보에 필요한 내용을 필드로 정의
public class MemberDTO {
    private Long memberNum;
    private String birthDateFormatted;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberYear;
    private String memberMonth;
    private String memberDay;
    private String memberGender;
    private String memberPno;
    private LocalDate createdAt;
    private String nickname;

    public MemberDTO(String memberEmail, String memberPassword, String memberName,
                     String memberYear, String memberMonth, String memberDay,
                     String memberGender, String memberPno, String birthDateFormatted,
                     String nickname, Long memberNum) {
        this();
        this.memberNum = memberNum;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberYear = memberYear;
        this.memberMonth = memberMonth;
        this.memberDay = memberDay;
        this.memberGender = memberGender;
        this.memberPno = memberPno;
        this.birthDateFormatted = birthDateFormatted;
        this.nickname = nickname;
    }

    public MemberDTO() {
        this.createdAt = LocalDate.now();
    }

    public static MemberDTO fromEntity(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberNum(memberEntity.getMemberNum());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setBirthDateFormatted(memberEntity.getBirthDateFormatted());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberPno(memberEntity.getMemberPno());
        memberDTO.setNickname(memberEntity.getNickname());
        return memberDTO;
    }


}

