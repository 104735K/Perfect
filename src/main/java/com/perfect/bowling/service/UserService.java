package com.perfect.bowling.service;

import com.perfect.bowling.dto.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    void saveUser (UserDto userDto);
    List<UserDto> findUsers();
    UserDto findById(Long userId);
    String findNameById(Long userId);
    List<UserDto> updateUser (List<UserDto> userDto);
    void deleteUser (Long userId);
}
