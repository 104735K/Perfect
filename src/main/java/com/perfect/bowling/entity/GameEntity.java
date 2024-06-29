package com.perfect.bowling.entity;

import com.perfect.bowling.dto.GameDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column
    private Date gameDate;

    public static GameEntity saveGameEntity (GameDto gameDto) {
        GameEntity gameEntity1 = new GameEntity();
        gameEntity1.setGameId(gameDto.getGameId());
        gameEntity1.setGameDate(gameDto.getGameDate());
        return gameEntity1;
    }
}
