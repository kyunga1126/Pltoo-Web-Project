package com.pltoo.membership.dto;

import com.pltoo.membership.entity.GameEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameDTO {
    private Long memberNum;
    private String memberGame;
    private String B01;
    private String B02;
    private String B03;
    private String B04;
    private String B05;
    private String B06;
    private String B07;
    private String B08;
    private String B09;
    private String B10;

    public GameDTO(String memberGame) {
        this.memberGame = memberGame;
    }

    public static GameDTO fromEntity(GameEntity gameEntity) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setMemberGame(gameEntity.getMemberGame());
        return gameDTO;
    }

}
