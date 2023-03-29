package com.erich.exam.controllers;

import static com.erich.exam.util.Path.PATH;

import com.erich.exam.dto.CategoryDto;
import com.erich.exam.services.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PATH + "category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto category){
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CategoryDto category){
        return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<?> getCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> deleteByCategoryId(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
