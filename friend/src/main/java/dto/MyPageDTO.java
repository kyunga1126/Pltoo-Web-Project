package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageDTO {
    private String profileImg;
    private String  profile;
    private Integer friendNum;
    private Integer age;
    private String friendList;

    public MyPageDTO(String profileImg, String profile, Integer friendNu, Integer age, String friendList) {
        this.profileImg = profileImg;
        this.profile = profile;
        this.friendNum = friendNum;
        this.age = age;
        this.friendList = friendList;
    }

}
