package com.example.avito.Ad;

import com.example.avito.Ad.dto.ReviewsDto;
import com.example.avito.category.Category;
import com.example.avito.category.dto.CategoryDto;
import com.example.avito.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(indexName = "ads")
@Table(name = "ads" )
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Field(type = FieldType.Text)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false , referencedColumnName = "id")
    @Field(type = FieldType.Object)
    private Category category;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
    private String photo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdStatus status;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews;

}

