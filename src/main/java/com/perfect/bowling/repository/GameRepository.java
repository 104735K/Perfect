package com.perfect.bowling.repository;

import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    List<GameEntity> findByGameDateBetween(Date startDate, Date endDate);
}
