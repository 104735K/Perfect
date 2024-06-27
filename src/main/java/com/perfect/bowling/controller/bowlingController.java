package com.perfect.bowling.controller;

import com.perfect.bowling.dto.GameDto;
import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.service.GameService;
import com.perfect.bowling.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class bowlingController {

    private UserService userService;
    private GameService gameService;

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> userDtoList = userService.findUsers();
        model.addAttribute("userList", userDtoList);
        return "users";
    }

    @GetMapping("/add")
    public String addForm() {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute UserDto userDto) {
       userService.saveUser(userDto);
       return "redirect:/bowling/users";
    }

    @GetMapping("/update")
    public String updateForm(Model model) {
        List<UserDto> userDtoList = userService.findUsers();
        model.addAttribute("updateUser", userDtoList);
        return "updateUser";
    }

    @PostMapping("/update/users")
    public String updateUser(HttpServletRequest request, HttpServletResponse response, Model model) {
            String[] userId = request.getParameterValues("userId");
            String[] names = request.getParameterValues("name");
            String[] englishNames = request.getParameterValues("englishName");
            String[] birthDates = request.getParameterValues("birthDate");
            String[] lunarSolars = request.getParameterValues("lunarSolar");
            String[] phoneNumbers = request.getParameterValues("phoneNumber");
            String[] addresses = request.getParameterValues("address");

            List<UserDto> userDtos = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                UserDto userDto = new UserDto();
                userDto.setUserId(Long.valueOf(userId[i]));
                userDto.setName(names[i]);
                userDto.setEnglishName(englishNames[i]);

                if (birthDates[i] != null && !birthDates[i].isEmpty()) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthDate = dateFormat.parse(birthDates[i]);
                        userDto.setBirthDate(birthDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                userDto.setLunarSolar(lunarSolars[i]);

                String[] gender = request.getParameterValues("gender_" + i);
                String genderString = Arrays.toString(gender);
                genderString = genderString.replace("[", "").replace("]", "");
                userDto.setGender(genderString);

                userDto.setPhoneNumber(phoneNumbers[i]);
                userDto.setAddress(addresses[i]);

                userDtos.add(userDto);
            }

                List<UserDto> updatedUsers = userService.updateUser(userDtos);
                model.addAttribute("updatedUsers", updatedUsers);

        return "redirect:/bowling/users";
    }

    @GetMapping("/games")
    public String games(Model model) {
        List<GameDto> gameDtos = new ArrayList<>();
        model.addAttribute("gameList", gameDtos);
        return "games";
    }
    @GetMapping("/newGame")
    public String gameAddForm() {
        return "newGame";
    }

    @PostMapping("/addGame")
    public String addGame(@ModelAttribute GameDto gameDto) {
        gameService.saveGame(gameDto);
        return "redirect:/bowling/games";
    }


}
