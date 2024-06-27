package com.perfect.bowling.serviceImpl;

import com.perfect.bowling.dto.GameDto;
import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.repository.GameRepository;
import com.perfect.bowling.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    @Override
    public void saveGame(GameDto gameDto) {
        GameEntity gameEntity = GameEntity.saveGames(gameDto);
        gameRepository.save(gameEntity);
    }

    @Override
    public List<GameDto> findGames() {
        List<GameEntity> gameEntities = gameRepository.findAll();
        List<GameDto> gameDtos = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            gameDtos.add(GameDto.toGameDto(gameEntity));
        }
        return gameDtos;
    }

    @Override
    public GameDto updateGame(GameDto gameDto) {
        return null;
    }

    @Override
    public void deleteGame(GameDto gameDto) {

    }
}
