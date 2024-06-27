package com.perfect.bowling.entity;

import com.perfect.bowling.dto.UserDto;
import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.standard.expression.IStandardConversionService;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column
    private String englishName;

    @Column
    private Date birthDate;

    @Column
    private String lunarSolar;

    @Column
    private String gender;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    public static UserEntity toSaveEntity (UserDto userDto) {
        UserEntity  userEntity = new UserEntity();
        userEntity.setUserId(userDto.getUserId());
        userEntity.setName(userDto.getName());
        userEntity.setEnglishName(userDto.getEnglishName());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setLunarSolar(userDto.getLunarSolar());
        userEntity.setGender(userDto.getGender());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setAddress(userDto.getAddress());
        return userEntity;
    }
}
