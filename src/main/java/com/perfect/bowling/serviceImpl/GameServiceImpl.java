package com.perfect.bowling.serviceImpl;

import com.perfect.bowling.dto.GameDto;
import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.repository.GameRepository;
import com.perfect.bowling.service.GameService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.IScope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    @Override
    public void saveGame(GameDto gameDto) {
        GameEntity gameEntity = GameEntity.saveGameEntity(gameDto);
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
    public GameDto findById(Long gameId) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if (gameEntity.isPresent()) {
            GameEntity gameEntity1 = gameEntity.get();
            return GameDto.toGameDto(gameEntity1);
        }
        return null;
    }

    @Override
    public GameDto updateGame(GameDto gameDto) {
        GameEntity gameEntity = GameEntity.updateGameEntity(gameDto);
        gameRepository.save(gameEntity);
        return findById(gameDto.getGameId());
    }

    @Override
    public void deleteGame(GameDto gameDto) {
    }
}
