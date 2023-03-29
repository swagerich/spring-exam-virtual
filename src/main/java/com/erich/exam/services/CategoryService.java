package com.erich.exam.services;

import com.erich.exam.dto.CategoryDto;
import com.erich.exam.entity.Category;

import java.util.Set;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto category);

    Set<CategoryDto> getCategories();

    CategoryDto getCategoryById(Long idCategory);

    void deleteCategoryById(Long idCategory);

}
