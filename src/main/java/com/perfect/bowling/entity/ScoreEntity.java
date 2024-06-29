package com.perfect.bowling.entity;

import com.perfect.bowling.dto.ScoreDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "score")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @Column
    private Long gameId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity userId;

    @Column
    private int scoreG1;

    @Column
    private int scoreG2;

    @Column
    private int scoreG3;

    @Column
    private int scoreG4;

    @Column
    private int handicap;

    @Column
    private int totalScore;

    @Column
    private int avgScore;

    public static ScoreEntity saveScore (ScoreDto scoreDto) {
        ScoreEntity scoreEntity = new ScoreEntity();

        scoreEntity.setScoreId(scoreDto.getScoreId());
        scoreEntity.setGameId(scoreDto.getGameId());

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(scoreDto.getUserId());
        scoreEntity.setUserId(userEntity);

        scoreEntity.setScoreG1(scoreDto.getScoreG1());
        scoreEntity.setScoreG2(scoreDto.getScoreG2());
        scoreEntity.setScoreG3(scoreDto.getScoreG3());
        scoreEntity.setScoreG4(scoreDto.getScoreG4());
        scoreEntity.setHandicap(scoreDto.getHandicap());
        scoreEntity.setTotalScore(scoreDto.getTotalScore());
        scoreEntity.setAvgScore(scoreDto.getAvgScore());

        return scoreEntity;
    }
}
