package com.perfect.bowling.entity;

import com.perfect.bowling.dto.ScoreDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Getter
@Setter
@Table(name = "score")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private GameEntity gameId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity userId;

    @Column
    private String userName;

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

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(scoreDto.getGameId());
        scoreEntity.setGameId(gameEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(scoreDto.getUserId());
        scoreEntity.setUserId(userEntity);

        scoreEntity.setUserName(scoreDto.getUserName());
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
