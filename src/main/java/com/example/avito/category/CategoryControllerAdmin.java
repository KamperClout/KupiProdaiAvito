package com.example.avito.category;

import com.example.avito.category.dto.CategoryDto;
import com.example.avito.category.dto.NewCategoryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class CategoryControllerAdmin {
  private final CategoryService categoryService;

  @PostMapping()
    public CategoryDto createCategory(@Valid @RequestBody NewCategoryDto newCategoryDto){
      return categoryService.createCategory(newCategoryDto);
  }

  @DeleteMapping(path = "/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
      categoryService.deleteCategory(categoryId);
  }

  @PutMapping(path = "/{categoryId}")
    public CategoryDto updateCategory(@PathVariable Long categoryId,
                                      @Valid @RequestBody NewCategoryDto newCategoryDto){
      return categoryService.updateCategory(categoryId, newCategoryDto);
  }
}
