package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "mypage")
public class MyPage {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private MemberEntity membership;

    private String profileImg;
    private String profile;
    private Integer friendNum;
    private Integer age;
    private String friendList;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    // nickname
    public void setId(Integer id) {
        this.id = id;
    }

    public MemberEntity getMembership() {
        return membership;
    }

    public void setMembership(MemberEntity membership) {
        this.membership = membership;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(Integer friendNum) {
        this.friendNum = friendNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFriendList() {
        return friendList;
    }

    public void setFriendList(String friendList) {
        this.friendList = friendList;
    }
}
