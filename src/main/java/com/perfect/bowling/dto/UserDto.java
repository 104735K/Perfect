package com.perfect.bowling.dto;

import com.perfect.bowling.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String englishName;

    @DateTimeFormat(pattern = "yy-MM-dd")
    private Date birthDate;

    private String gender;
    private String phoneNumber;
    private String address;

    public static UserDto toUserDto (UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setName(userEntity.getName());
        userDto.setEnglishName(userEntity.getEnglishName());
        userDto.setBirthDate(userEntity.getBirthDate());
        userDto.setGender(userEntity.getGender());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDto.setAddress(userEntity.getAddress());
        return userDto;
    }
}
