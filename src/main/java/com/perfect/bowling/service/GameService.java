package com.perfect.bowling.service;

import com.perfect.bowling.dto.GameDto;

import java.util.List;

public interface GameService {
    void saveGame (GameDto gameDto);
    List<GameDto> findGames();
    GameDto updateGame (GameDto gameDto);
    void deleteGame (GameDto gameDto);
}
