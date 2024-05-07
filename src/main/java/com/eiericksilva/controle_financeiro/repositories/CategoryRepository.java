package com.eiericksilva.controle_financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eiericksilva.controle_financeiro.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
