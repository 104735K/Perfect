package com.perfect.bowling.service;

import com.perfect.bowling.dto.GameDto;
import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.entity.ScoreEntity;

import java.util.List;

public interface GameService {
    void saveGame (GameDto gameDto);
    List<GameDto> findGames();
    GameDto findById(Long gameId);
    GameDto updateGame (GameDto gameDto);
    void deleteGame (Long gameId);
}
