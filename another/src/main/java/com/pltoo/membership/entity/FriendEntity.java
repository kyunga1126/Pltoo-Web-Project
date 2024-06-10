package com.pltoo.membership.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "myprofile")
@Entity
public class FriendEntity {

        @Id //MemberEntity의 id 가져오기
        @Column(name = "email", length = 50, unique = true) // 저장이 아닌 업데이트update가 되게끔.
        private String memberEmail;

        @ManyToOne
        @JoinColumn(name = "id")
        private MyPageEntity member;

        @ManyToOne
        @JoinColumn(name = "friend_id")
        private MyPageEntity friend;
}
