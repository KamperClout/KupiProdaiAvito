package com.example.avito.Ad.dto;

import com.example.avito.Ad.AdStatus;
import com.example.avito.category.Category;
import com.example.avito.user.User;
import com.example.avito.user.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    @NotEmpty
    private String description;
    @NotNull
    private Category category;
    @PositiveOrZero
    @NotNull
    private Double price;
    private User userName;
    private String photo;
    private AdStatus status;
    //@OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ReviewsDto> reviews = new ArrayList<>();
    private List<ReviewsDto> reviews;
}
