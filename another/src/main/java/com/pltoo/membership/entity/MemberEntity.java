package com.pltoo.membership.entity;
// 테이블 역할

import com.pltoo.membership.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Table(name = "membership")
//@SequenceGenerator(
//        name = "PLTOO_SEQ_GENERATOR",
//        sequenceName = "PLTOO_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 5)//낮은 값으로 설정. 시퀀스 번호가 낭비 방지
public class MemberEntity {
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLTOO_SEQ_GENERATOR")// SEQUENCE
    @Id //pk저장
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long memberNum;

    @Column(name = "email", length = 50, unique = true)
    private String memberEmail;

    @Column(name = "login_at", nullable = false)
    private LocalDateTime loginAt = LocalDateTime.now();

    @Column(name = "login_fail", nullable = false)
    private int loginFail = 0;

    @Column(name = "pwd", length = 16, nullable = false)
    private String memberPassword;

    @Column(name = "name", length = 10, nullable = false)
    private String memberName;

    @Column(name = "birth", length = 50, nullable = true)
    private String birthDateFormatted;

    @Column(name = "pno", length = 13, nullable = false)
    private String memberPno;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "gender", length = 3)
    private String memberGender;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "nickname", length = 8)
    private String nickname;

    // MyPage 구현되면 삭제 후 MyPage로 이동 예정.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameEntity> games;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setBirthDateFormatted(memberDTO.getBirthDateFormatted()); // 여기서 birthDateFormatted를 제대로 설정
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberPno(memberDTO.getMemberPno());
        memberEntity.setLoginAt(memberDTO.getCreatedAt() != null ? memberDTO.getCreatedAt().atStartOfDay() : LocalDateTime.now());
        memberEntity.setNickname(memberDTO.getNickname());

        return memberEntity;
    }
}
