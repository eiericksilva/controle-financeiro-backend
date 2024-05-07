package com.eiericksilva.controle_financeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.CategoryRepository;
import com.eiericksilva.controle_financeiro.repositories.SubcategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category addSubcategory(Long categoryId, Subcategory subcategory) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(categoryId));
        subcategoryRepository.save(subcategory);

        category.addSubcategory(subcategory);

        return categoryRepository.save(category);
    }

}
