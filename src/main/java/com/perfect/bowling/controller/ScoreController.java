package com.perfect.bowling.controller;

import com.perfect.bowling.dto.GameDto;
import com.perfect.bowling.dto.ScoreDto;
import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.service.GameService;
import com.perfect.bowling.service.ScoreService;
import com.perfect.bowling.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class ScoreController {
    private UserService userService;
    private ScoreService scoreService;
    private GameService gameService;

    @GetMapping("/game/scores/{gameId}")
    public String scores(Model model, @PathVariable Long gameId) {
        List<ScoreDto> scoreDtos = scoreService.getScoresByGameId(gameId);
        model.addAttribute("scoreList", scoreDtos);
        return "gameScore";
    }

    @PostMapping("/games/{gameId}")
    public String saveScore(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("gameId") Long gameId) {
        String[] userId = request.getParameterValues("userId");
        String[] scoreG1 = request.getParameterValues("scoreG1");
        String[] scoreG2 = request.getParameterValues("scoreG2");
        String[] scoreG3 = request.getParameterValues("scoreG3");
        String[] scoreG4 = request.getParameterValues("scoreG4");
        String[] handicap = request.getParameterValues("handicap");

        List<ScoreDto> scoreDtos = new ArrayList<>();

        for (int i = 0; i < userId.length; i++) {
                ScoreDto scoreDto = new ScoreDto();
                scoreDto.setUserId(Long.valueOf(userId[i]));
                scoreDto.setGameId(gameId);
                scoreDto.setScoreG1(Integer.parseInt(scoreG1[i]));
                scoreDto.setScoreG2(Integer.parseInt(scoreG2[i]));
                scoreDto.setScoreG3(Integer.parseInt(scoreG3[i]));
                scoreDto.setScoreG4(Integer.parseInt(scoreG4[i]));
                scoreDto.setHandicap(Integer.parseInt(handicap[i]));

                String userName = String.valueOf(userService.findNameById(Long.valueOf(userId[i])));
                scoreDto.setUserName(userName);

                int totalScore = Integer.parseInt(scoreG1[i]) + Integer.parseInt(scoreG2[i]) + Integer.parseInt(scoreG3[i]) + Integer.parseInt(scoreG4[i]) + Integer.parseInt(handicap[i]);
                scoreDto.setTotalScore(totalScore);

                int avgScore = totalScore / 4;
                scoreDto.setAvgScore(avgScore);

                scoreDtos.add(scoreDto);
            }

        List<ScoreDto> scoreDto1 = scoreService.saveScore(scoreDtos);
        model.addAttribute("scoreDto1", scoreDto1);
        return "redirect:/bowling/games/" + gameId;
    }



    @GetMapping("/game/scores/update")
    public String updateForm(Model model) {
        List<ScoreDto> scoreDtoList = scoreService.findScore();
        model.addAttribute("updateScores", scoreDtoList);
        return "gameScore";
    }

    @PostMapping("/game/scores/updateScores")
    public String updateScores(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Long gameId) {
        String[] scoreId = request.getParameterValues("scoreId");
        String[] userId = request.getParameterValues("userId");
        String[] gameIds = request.getParameterValues("gameId");
        String[] scoreG1 = request.getParameterValues("scoreG1");
        String[] scoreG2 = request.getParameterValues("scoreG2");
        String[] scoreG3 = request.getParameterValues("scoreG3");
        String[] scoreG4 = request.getParameterValues("scoreG4");
        String[] handicap = request.getParameterValues("handicap");

        List<ScoreDto> scoreDtos = new ArrayList<>();
        for (int i = 0; i < userId.length; i++) {
                ScoreDto scoreDto = new ScoreDto();
                scoreDto.setScoreId(Long.valueOf(scoreId[i]));
                scoreDto.setUserId(Long.valueOf(userId[i]));
                scoreDto.setGameId(Long.valueOf(gameIds[i]));
                scoreDto.setScoreG1(Integer.parseInt(scoreG1[i]));
                scoreDto.setScoreG2(Integer.parseInt(scoreG2[i]));
                scoreDto.setScoreG3(Integer.parseInt(scoreG3[i]));
                scoreDto.setScoreG4(Integer.parseInt(scoreG4[i]));
                scoreDto.setHandicap(Integer.parseInt(handicap[i]));

                String userName = String.valueOf(userService.findNameById(Long.valueOf(userId[i])));
                scoreDto.setUserName(userName);

                int totalScore = Integer.parseInt(scoreG1[i]) + Integer.parseInt(scoreG2[i]) + Integer.parseInt(scoreG3[i]) + Integer.parseInt(scoreG4[i]) + Integer.parseInt(handicap[i]);
                scoreDto.setTotalScore(totalScore);

                int avgScore = totalScore / 4;
                scoreDto.setAvgScore(avgScore);

                scoreDtos.add(scoreDto);
            }

        List<ScoreDto> scoreDto1 = scoreService.updateScore(scoreDtos);
        model.addAttribute("updateScores", scoreDto1);
        return "redirect:/bowling/games/" + gameId;
    }

    @GetMapping("/game/scores/delete/{scoreId}")
    public String deleteScore(@PathVariable Long scoreId, HttpServletRequest request) {
        scoreService.deleteScore(scoreId);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer ;
    }
}

