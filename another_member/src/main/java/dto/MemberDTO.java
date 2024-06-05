package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MemberDTO {
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

    // 나는 기본생성자를 만들었다.
    public MemberDTO(String memberEmail, String memberPassword, String memberName,
                     String memberYear, String memberMonth, String memberDay,
                     String memberGender, String memberPno, String birthDateFormatted,
                     String nickname) {
        this();
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

}