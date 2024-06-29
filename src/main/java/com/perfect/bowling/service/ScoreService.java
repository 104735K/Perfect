package com.perfect.bowling.service;

import com.perfect.bowling.dto.ScoreDto;

import java.util.List;

public interface ScoreService {
    List<ScoreDto> saveScore (List<ScoreDto> scoreDtos);
    List<ScoreDto> findScore();
    ScoreDto findById(Long scoreId);
    List<ScoreDto> updateScore (List<ScoreDto> scoreDto);
    void deleteScore (ScoreDto scoreDto);
}
