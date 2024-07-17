package com.example.avito.Ad.dto;

import com.example.avito.Ad.Ad;
import com.example.avito.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewsDto {
    private Long id;
    private User user;
    private Ad ad;
    @NotBlank
    @Size(min = 0, max = 10)
    private int rating;
    @NotBlank
    @NotEmpty
    private String comment;
    private LocalDateTime created;
}
