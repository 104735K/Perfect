package com.perfect.bowling.serviceImpl;

import com.perfect.bowling.dto.UserDto;
import com.perfect.bowling.entity.UserEntity;
import com.perfect.bowling.repository.UserRepository;
import com.perfect.bowling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void saveUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.toSaveEntity(userDto);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDto> findUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDtos.add(UserDto.toUserDto(userEntity));
        }
        return userDtos;
    }

    @Override
    public UserDto findById(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity userEntity1 = userEntity.get();
            return UserDto.toUserDto(userEntity1);
        }
        return null;
    }

    @Override
    public String findNameById(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity userEntity1 = userEntity.get();
            return userEntity1.getName();
        }
        return null;
    }

    @Override
    public List<UserDto> updateUser(List<UserDto> userList) {
        List<UserDto> updatedUsers = new ArrayList<>();
        for (UserDto userDto : userList) {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(userDto.getUserId());
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity = optionalUserEntity.get();
                userEntity.setUserId(userDto.getUserId());
                userEntity.setName(userDto.getName());
                userEntity.setEnglishName(userDto.getEnglishName());
                userEntity.setBirthDate(userDto.getBirthDate());
                userEntity.setLunarSolar(userDto.getLunarSolar());
                userEntity.setGender(userDto.getGender());
                userEntity.setPhoneNumber(userDto.getPhoneNumber());
                userEntity.setAddress(userDto.getAddress());

                userRepository.save(userEntity);
                updatedUsers.add(UserDto.toUserDto(userEntity));
            }
        }
        return updatedUsers;
    }

    @Override
    public void deleteUser(String userId) {
        System.out.println(userId);
        Long test = Long.parseLong(userId);
        userRepository.deleteById(test);
    }
}
