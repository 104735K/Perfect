package com.perfect.bowling.repository;

import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
