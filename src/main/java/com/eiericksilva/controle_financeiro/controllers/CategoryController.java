package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public Category findCategorieById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PostMapping("{categoryId}/subcategories")
    public Category addSubcategory(@PathVariable Long categoryId, @RequestBody Subcategory subcategory) {
        return categoryService.addSubcategory(categoryId, subcategory);
    }
}
