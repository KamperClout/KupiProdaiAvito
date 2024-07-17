package com.example.avito.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "не должно быть пустым")
    private String name;
    @Email(message = "должно иметь формат адреса электронной почты")
    @NotBlank(message = "не должно быть пустым")
    private String email;
}