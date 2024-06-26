package com.perfect.bowling.controller;

import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bowling")
@AllArgsConstructor
public class bowlingController {

    private UserService userService;

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
    public String updateUser(@ModelAttribute("updateUser") List<UserDto> userDtoList, Model model) {
        List<UserDto> userDtos = userService.updateUser(userDtoList);
        model.addAttribute("updateUser", userDtos);
        return "redirect:/bowling/users";
    }


}
