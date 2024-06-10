package com.pltoo.membership.entity;

import com.pltoo.membership.dto.GameDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "product_game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num", nullable = false)
    private Long num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)  // 변경된 부분
    private MemberEntity member;  // 이름 변경

    @Column(name = "game", length = 255, nullable = false)
    private String memberGame;

    public static GameEntity toGameEntity(GameDTO gameDTO, MemberEntity member) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setMemberGame(gameDTO.getMemberGame());
        gameEntity.setMember(member);  // 변수 이름 변경으로 인한 수정
        return gameEntity;
    }
}
