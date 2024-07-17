package com.example.avito.user;

import com.example.avito.user.dto.SignUpRequest;
import com.example.avito.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
                );
    }
}
