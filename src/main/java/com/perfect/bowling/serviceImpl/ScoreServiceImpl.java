package com.perfect.bowling.serviceImpl;

import com.perfect.bowling.dto.ScoreDto;
import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.entity.ScoreEntity;
import com.perfect.bowling.entity.UserEntity;
import com.perfect.bowling.repository.GameRepository;
import com.perfect.bowling.repository.ScoreRepository;
import com.perfect.bowling.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final GameRepository gameRepository;
    @Override
    public List<ScoreDto> saveScore(List<ScoreDto> scoreList) {
        List<ScoreDto> scoreDtos = new ArrayList<>();
        for (ScoreDto scoreDto1 : scoreList) {

                ScoreEntity scoreEntity1 = new ScoreEntity();

                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(scoreDto1.getUserId());
                scoreEntity1.setUserId(userEntity);

                GameEntity gameEntity = new GameEntity();
                gameEntity.setGameId(scoreDto1.getGameId());
                scoreEntity1.setGameId(gameEntity);

                scoreEntity1.setUserName(scoreDto1.getUserName());
                scoreEntity1.setScoreId(scoreDto1.getScoreId());
                scoreEntity1.setScoreG1(scoreDto1.getScoreG1());
                scoreEntity1.setScoreG2(scoreDto1.getScoreG2());
                scoreEntity1.setScoreG3(scoreDto1.getScoreG3());
                scoreEntity1.setScoreG4(scoreDto1.getScoreG4());
                scoreEntity1.setHandicap(scoreDto1.getHandicap());
                scoreEntity1.setTotalScore(scoreDto1.getTotalScore());
                scoreEntity1.setAvgScore(scoreDto1.getAvgScore());

                scoreRepository.save(scoreEntity1);
                scoreDtos.add(ScoreDto.toScoreDto(scoreEntity1));
            }
        return scoreDtos;
    }

    @Override
    public List<ScoreDto> findScore() {
        List<ScoreEntity> scoreEntities = scoreRepository.findAll();
        List<ScoreDto> scoreDtos = new ArrayList<>();
        for (ScoreEntity scoreEntity : scoreEntities) {
            scoreDtos.add(ScoreDto.toScoreDto(scoreEntity));
        }
        return scoreDtos;
    }

    @Override
    public ScoreDto findById(Long scoreId) {
        Optional<ScoreEntity> scoreEntity = scoreRepository.findById(scoreId);
        if (scoreEntity.isPresent()) {
            ScoreEntity scoreEntity1 = scoreEntity.get();
            return ScoreDto.toScoreDto(scoreEntity1);
        }
        return null;
    }

    @Override
    public List<ScoreDto> updateScore(List<ScoreDto> scoreDtoList) {
        List<ScoreDto> updateScores = new ArrayList<>();
        for (ScoreDto scoreDto1 : scoreDtoList) {
            Optional<ScoreEntity> optionalScoreEntity = scoreRepository.findById(scoreDto1.getScoreId());
            if (optionalScoreEntity.isPresent()) {
                ScoreEntity scoreEntity = optionalScoreEntity.get();
                scoreEntity.setScoreG1(scoreDto1.getScoreG1());
                scoreEntity.setScoreG2(scoreDto1.getScoreG2());
                scoreEntity.setScoreG3(scoreDto1.getScoreG3());
                scoreEntity.setScoreG4(scoreDto1.getScoreG4());
                scoreEntity.setHandicap(scoreDto1.getHandicap());
                scoreEntity.setTotalScore(scoreDto1.getTotalScore());
                scoreEntity.setAvgScore(scoreDto1.getAvgScore());

                scoreRepository.save(scoreEntity);
                updateScores.add(ScoreDto.toScoreDto(scoreEntity));
            }
        }
        return updateScores;
    }

    @Override
    public void deleteScore(ScoreDto scoreDto) {

    }

    @Override
    public List<ScoreDto> getScoresByGameId(Long gameId) {
        List<ScoreEntity> scoreEntities = scoreRepository.findAll();
        List<ScoreDto> scoreDtos = new ArrayList<>();
        for (ScoreEntity scoreEntity : scoreEntities) {
            if (scoreEntity.getGameId().getGameId().equals(gameId)) {
                scoreDtos.add(ScoreDto.toScoreDto(scoreEntity));
            }
        }
        return scoreDtos;
    }
}
