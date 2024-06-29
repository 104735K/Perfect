package com.perfect.bowling.controller;

import com.perfect.bowling.dto.ScoreDto;
import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.service.ScoreService;
import com.perfect.bowling.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class ScoreController {
    private UserService userService;
    private ScoreService scoreService;

    @GetMapping("/scores/{gameId}")
    public String scores(Model model, @PathVariable Long gameId) {
        List<ScoreDto> scoreDtos = scoreService.findScore();
        model.addAttribute("scoreList", scoreDtos);
        return "gameScore";
    }

    @GetMapping("/scores/add")
    public String scoreForm(Model model) {
        List<UserDto> userDtos = userService.findUsers();
        model.addAttribute("users", userDtos);
        return "addScore";
    }

    @PostMapping("/scores/addScore")
    public String saveScore(HttpServletRequest request, HttpServletResponse response, Model model) {
        String[] scoreId = request.getParameterValues("scoreId");
        String[] userId = request.getParameterValues("userId");
        String[] gameId = request.getParameterValues("gameId");
        String[] scoreG1 = request.getParameterValues("scoreG1");
        String[] scoreG2 = request.getParameterValues("scoreG2");
        String[] scoreG3 = request.getParameterValues("scoreG3");
        String[] scoreG4 = request.getParameterValues("scoreG4");
        String[] handicap = request.getParameterValues("handicap");
        String[] totalScore = request.getParameterValues("totalScore");
        String[] avgScore = request.getParameterValues("avgScore");

        List<ScoreDto> scoreDtos = new ArrayList<>();
        for (int i = 0; i < scoreId.length; i++) {
            ScoreDto scoreDto = new ScoreDto();
            scoreDto.setScoreId(Long.valueOf(scoreId[i]));
            scoreDto.setUserId(Long.valueOf(userId[i]));
            scoreDto.setGameId(Long.valueOf(gameId[i]));
            scoreDto.setScoreG1(Integer.parseInt(scoreG1[i]));
            scoreDto.setScoreG2(Integer.parseInt(scoreG2[i]));
            scoreDto.setScoreG3(Integer.parseInt(scoreG3[i]));
            scoreDto.setScoreG4(Integer.parseInt(scoreG4[i]));
            scoreDto.setHandicap(Integer.parseInt(handicap[i]));
            scoreDto.setTotalScore(Integer.parseInt(totalScore[i]));
            scoreDto.setAvgScore(Integer.parseInt(avgScore[i]));

            scoreDtos.add(scoreDto);
        }

        List<ScoreDto> scoreDtos1 = scoreService.saveScore(scoreDtos);
        model.addAttribute("score", scoreDtos1);

        return "redirect:/bowling/games/" + gameId;
    }
}
