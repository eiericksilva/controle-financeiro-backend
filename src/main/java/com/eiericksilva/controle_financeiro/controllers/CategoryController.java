package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /*CREATE*/
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PostMapping("{categoryId}/subcategories")
    public Category addSubcategory(@PathVariable Long categoryId, @RequestBody Subcategory subcategory) {
        return categoryService.addSubcategory(categoryId, subcategory);
    }

    /*READ*/
    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public Category findCategorieById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    /*UPDATE*/
    @PutMapping(value = "/{categoryId}")
    public Category update(@PathVariable Long categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    /*DELETE*/
    @DeleteMapping(value = "/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @DeleteMapping("{categoryId}/subcategories/{subcategoryId}")
    public Category deleteSubcategory(@PathVariable Long categoryId, @PathVariable Long subcategoryId) {
        return categoryService.deleteSubcategory(categoryId, subcategoryId);
    }




}
