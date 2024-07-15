package com.perfect.bowling.controller;

import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.entity.GameEntity;
import com.perfect.bowling.entity.ScoreEntity;
import com.perfect.bowling.entity.UserEntity;
import com.perfect.bowling.repository.GameRepository;
import com.perfect.bowling.repository.ScoreRepository;
import com.perfect.bowling.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class ExcelController {

    private UserRepository userRepository;
    private GameRepository gameRepository;
    private ScoreRepository scoreRepository;

    @GetMapping("/downloadUser")
    public void downloadUser(HttpServletResponse response) throws IOException {

        try {
            List<UserEntity> users = userRepository.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("회원명단");
            int rowNo = 0;

            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("회원ID");
            headerRow.createCell(1).setCellValue("이름");
            headerRow.createCell(2).setCellValue("영문명");
            headerRow.createCell(3).setCellValue("생년월일");
            headerRow.createCell(4).setCellValue("음/양");
            headerRow.createCell(5).setCellValue("성별");
            headerRow.createCell(6).setCellValue("번호");
            headerRow.createCell(7).setCellValue("주소");

            for (UserEntity user : users) {
                Row dataRow = sheet.createRow(rowNo++);
                dataRow.createCell(0).setCellValue(user.getUserId());
                dataRow.createCell(1).setCellValue(user.getName());
                dataRow.createCell(2).setCellValue(user.getEnglishName());

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = "";
                if (user.getBirthDate() != null) {
                    formattedDate = formatter.format(user.getBirthDate());
                } else {
                    formattedDate = " ";
                }
                dataRow.createCell(3).setCellValue(formattedDate);
                dataRow.createCell(4).setCellValue(user.getLunarSolar());
                dataRow.createCell(5).setCellValue(user.getGender());
                dataRow.createCell(6).setCellValue(user.getPhoneNumber());
                dataRow.createCell(7).setCellValue(user.getAddress());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/downloadScoresByGameId/{gameId}")
    public void downloadScoresByGameId(HttpServletResponse response, @PathVariable Long gameId, GameEntity game) throws IOException {

        try {
            Optional<GameEntity> gameEntities = gameRepository.findById(gameId);

            if (gameEntities.isPresent()) {
                GameEntity gameEntity = gameEntities.get();

                List<ScoreEntity> scoreEntities = scoreRepository.findByGameId(game);

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("게임점수");
                int rowNo = 0;

                Row headerRow = sheet.createRow(rowNo++);
                headerRow.createCell(0).setCellValue("게임ID");
                headerRow.createCell(1).setCellValue("게임날짜");

                Row dataRow = sheet.createRow(rowNo++);
                dataRow.createCell(0).setCellValue(gameEntity.getGameId());

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = (gameEntity.getGameDate() != null) ? formatter.format(gameEntity.getGameDate()) : "";
                dataRow.createCell(1).setCellValue(formattedDate);

                rowNo++;

                Row memberHeaderRow = sheet.createRow(rowNo++);
                memberHeaderRow.createCell(0).setCellValue("회원ID");
                memberHeaderRow.createCell(1).setCellValue("이름");
                memberHeaderRow.createCell(2).setCellValue("G1");
                memberHeaderRow.createCell(3).setCellValue("G2");
                memberHeaderRow.createCell(4).setCellValue("G3");
                memberHeaderRow.createCell(5).setCellValue("G4");
                memberHeaderRow.createCell(6).setCellValue("핸디");
                memberHeaderRow.createCell(7).setCellValue("총점수");
                memberHeaderRow.createCell(8).setCellValue("평균");

                for (ScoreEntity scoreEntity : scoreEntities) {
                    Row MemberDataRow = sheet.createRow(rowNo++);
                    MemberDataRow.createCell(0).setCellValue(scoreEntity.getUserId().getUserId());
                    MemberDataRow.createCell(1).setCellValue(scoreEntity.getUserName());
                    MemberDataRow.createCell(2).setCellValue(scoreEntity.getScoreG1());
                    MemberDataRow.createCell(3).setCellValue(scoreEntity.getScoreG2());
                    MemberDataRow.createCell(4).setCellValue(scoreEntity.getScoreG3());
                    MemberDataRow.createCell(5).setCellValue(scoreEntity.getScoreG4());
                    MemberDataRow.createCell(6).setCellValue(scoreEntity.getHandicap());
                    MemberDataRow.createCell(7).setCellValue(scoreEntity.getTotalScore());
                    MemberDataRow.createCell(8).setCellValue(scoreEntity.getAvgScore());
                }

                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

                OutputStream outputStream = response.getOutputStream();
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } else {
                response.getWriter().println("해당 게임ID에 대한 데이터가 존재하지 않습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
