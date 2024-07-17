package com.example.avito.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(message = "не должно быть пустым")
    @Size(min = 1, max = 50)
    private String name;
}
