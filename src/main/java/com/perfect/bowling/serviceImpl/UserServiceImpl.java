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
    public List<UserDto> updateUser(List<UserDto> userList) {
        List<UserDto> updatedUsers = new ArrayList<>();
        for (UserDto userDto : userList) {
            UserDto updatedUser = (UserDto) updateUser((List<UserDto>) userDto);
            updatedUsers.add(updatedUser);
        }
        return updatedUsers;
    }

    @Override
    public void deleteUser(UserDto userDto) {

    }
}
