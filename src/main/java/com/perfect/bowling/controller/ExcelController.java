package com.perfect.bowling.controller;

import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.entity.UserEntity;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class ExcelController {

    private UserRepository userRepository;

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
}
