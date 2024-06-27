package com.perfect.bowling.dto;

import com.perfect.bowling.entity.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long gameId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gameDate;

    public static GameDto toGameDto (GameEntity gameEntity) {
        GameDto gameDto = new GameDto();
        gameDto.setGameId(gameEntity.getGameId());
        gameDto.setGameDate(gameEntity.getGameDate());

        return gameDto;
    }
}
