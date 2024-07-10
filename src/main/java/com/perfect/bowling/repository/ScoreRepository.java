package com.perfect.bowling.repository;

import com.perfect.bowling.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> { }
