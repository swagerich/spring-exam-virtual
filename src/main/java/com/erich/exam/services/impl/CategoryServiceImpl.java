package com.erich.exam.services.impl;

import com.erich.exam.dto.CategoryDto;
import com.erich.exam.entity.Category;
import com.erich.exam.exception.NotFoundException;
import com.erich.exam.repository.CategoryRepository;
import com.erich.exam.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto category) {
        Category cate = CategoryDto.toEntity(category);
        return CategoryDto.fromEntity(categoryRepo.save(cate));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(CategoryDto category) {
        Category cate = CategoryDto.toEntity(category);
        return CategoryDto.fromEntity(categoryRepo.save(cate));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CategoryDto> getCategories() {
        return Streamable.of(categoryRepo.findAll()).map(x -> CategoryDto.fromEntity(x))
                .stream().collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long idCategory) {

        return CategoryDto.fromEntity(categoryRepo.findById(idCategory)
                .orElseThrow(() -> new NotFoundException("id category not found")));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long idCategory) {
        if(idCategory == null){
            return;
        }
        categoryRepo.deleteById(idCategory);

    }
}
