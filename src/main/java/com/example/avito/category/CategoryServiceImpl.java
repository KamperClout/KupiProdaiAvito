package com.example.avito.category;

import com.example.avito.category.dto.CategoryDto;
import com.example.avito.category.dto.NewCategoryDto;
import com.example.avito.jpa.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;

    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        try {
            return CategoryMapper.toCategoryDto(repository.save(CategoryMapper.toCategory(newCategoryDto)));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("This category name is exists already in database");
        }
    }

    @Override
    public void deleteCategory(Long catId) {
        try {
            repository.deleteById(catId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(
                    String.format("Category with id = %s was not found", catId));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("The category is not empty");
        }
    }

    @Override
    public CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto) {
        Category category = repository.findById(catId)
                .orElseThrow(() -> new RuntimeException(String.format("Категория с id= %s не была найдена", catId)));
        if (category.getName().equals(newCategoryDto.getName())) {
            return CategoryMapper.toCategoryDto(category);
        }
        category.setName(newCategoryDto.getName());
        try {
            return CategoryMapper.toCategoryDto(repository.save(category));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Имя категории уже есть в бд");
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return repository.findAll().stream().map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        var category = repository.findById(catId).orElseThrow(()->new RuntimeException("Такой категории нет"));
        return CategoryMapper.toCategoryDto(category);
    }
}
