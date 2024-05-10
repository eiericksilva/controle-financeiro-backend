package com.eiericksilva.controle_financeiro.services;

import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.CategoryRepository;
import com.eiericksilva.controle_financeiro.repositories.SubcategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    /*CREATE*/
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

    /*READ*/
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /*UPDATE*/
    public Category updateCategory(Long categoryId, Category category) {
        Category categoryFound = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId));

        BeanUtils.copyProperties(category, categoryFound, "id");

        return categoryRepository.save(categoryFound);
    }

    /*DELETE*/
    public void deleteCategory(Long categoryId) {
        categoryRepository
                .delete(categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException(categoryId)));
    }

    public Category deleteSubcategory(Long categoryId, Long subcategoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(categoryId));
        Subcategory subcategoryToRemove = category.getSubcategories().stream()
                .filter((subcategory) -> subcategory.getId().equals(subcategoryId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(subcategoryId));
        category.getSubcategories().remove(subcategoryToRemove);
        return categoryRepository.save(category);
    }
}
