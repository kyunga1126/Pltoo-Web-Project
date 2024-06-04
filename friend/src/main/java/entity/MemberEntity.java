package entity;

import dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@Table(name = "membership")
public class MemberEntity {
    @Id //pk저장
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "id", nullable = false)
    private int membernum;

    @Column(name = "email", length = 50, unique = true)// unique 제약조건 추가
    private String memberEmail;

    @Column(name = "login_at")
    private LocalDateTime loginAt;

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
    private LocalDateTime createdAt;

    @Column(name = "nickname", length = 8)
    private String nickname;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setBirthDateFormatted(memberDTO.getBirthDateFormatted()); // 여기서 birthDateFormatted를 제대로 설정
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberPno(memberDTO.getMemberPno());
        memberEntity.setCreatedAt(memberDTO.getCreatedAt() == null ? LocalDateTime.now() : memberDTO.getCreatedAt().atStartOfDay());
        memberEntity.setNickname(memberDTO.getNickname());

        return memberEntity;
    }

    public void setId(Integer userId) {

    }
}
