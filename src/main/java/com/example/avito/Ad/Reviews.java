package com.example.avito.Ad;

import com.example.avito.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false, referencedColumnName = "id")
    private Ad ad;
    private int rating;
    private String comment;
    private LocalDateTime created;
}
