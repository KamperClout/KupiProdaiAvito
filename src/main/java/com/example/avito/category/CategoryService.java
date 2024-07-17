package com.example.avito.category;

import com.example.avito.category.dto.CategoryDto;
import com.example.avito.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    void deleteCategory(Long catId);

    CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long catId);
}
