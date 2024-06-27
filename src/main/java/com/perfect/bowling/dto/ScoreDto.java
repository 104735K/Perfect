package com.perfect.bowling.dto;

import com.perfect.bowling.entity.ScoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.hibernate.boot.archive.scan.spi.ScanParameters;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    private Long scoreId;
    private Long gameId;
    private Long userId;
    private int scoreG1;
    private int scoreG2;
    private int scoreG3;
    private int scoreG4;
    private int handicap;
    private int totalScore;
    private int avgScore;

    public static ScoreDto toScoreDto (ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScoreId(scoreEntity.getScoreId());
        scoreDto.setGameId(scoreEntity.getGameId().getGameId());
        scoreDto.setUserId(scoreEntity.getUserId().getUserId());
        scoreDto.setScoreG1(scoreEntity.getScoreG1());
        scoreDto.setScoreG2(scoreEntity.getScoreG2());
        scoreDto.setScoreG3(scoreEntity.getScoreG3());
        scoreDto.setScoreG4(scoreEntity.getScoreG4());
        scoreDto.setHandicap(scoreEntity.getHandicap());
        scoreDto.setTotalScore(scoreEntity.getTotalScore());
        scoreDto.setAvgScore(scoreEntity.getAvgScore());

        return scoreDto;
    }


}
