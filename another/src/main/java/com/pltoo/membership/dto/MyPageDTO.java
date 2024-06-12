package com.pltoo.membership.dto;

import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyPageDTO {
    private Long memberNum;
    private String memberEmail;
    private String profileImage;
    private String profile;
    private String memberGender;
    private int friendNum;
    private String friendList;
    //마이페이지 받아온 정보
    private String myProfile;
    // 뷰에 데이터를 전달하기 위해 사용되므로, 필요한 데이터를 담고 있는 것이 일반적
    private String nickname;
    private String age;

    public static MyPageDTO fromEntity(MyPageEntity myPageEntity) {
        MyPageDTO  myPageDTO = new MyPageDTO();
        MemberEntity memberEntity = myPageEntity.getMemberEntity();
        myPageDTO.setMemberEmail(myPageEntity.getMemberEmail());
        myPageDTO.setProfileImage(myPageEntity.getProfileImage());
        myPageDTO.setProfile(myPageEntity.getProfile());
        myPageDTO.setFriendNum(myPageEntity.getFriendNum());
        myPageDTO.setFriendList(myPageEntity.getFriendList());
        myPageDTO.setNickname(memberEntity.getNickname());
        myPageDTO.setAge(calculateAge(memberEntity.getBirthDateFormatted()));
        return myPageDTO;
    }

    private static String calculateAge(String birthDateFormatted) {
        if (birthDateFormatted == null || birthDateFormatted.length() < 4) {
            return "N/A";
        }
        int birthYear = Integer.parseInt(birthDateFormatted.substring(0, 4));
        int currentYear = java.time.Year.now().getValue();
        return String.valueOf(currentYear - birthYear);
    }

}
