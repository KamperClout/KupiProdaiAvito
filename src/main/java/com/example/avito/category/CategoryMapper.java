package com.example.avito.category;

import com.example.avito.category.dto.CategoryDto;
import com.example.avito.category.dto.NewCategoryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    public Category toCategory(NewCategoryDto newCategoryDto) {
        return new Category(
                null,
                newCategoryDto.getName()
        );
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
