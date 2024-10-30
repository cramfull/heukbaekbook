package com.nhnacademy.heukbaekbookshop.category.repository;

import com.nhnacademy.heukbaekbookshop.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndParentCategory(String trimmedName, Category parentCategory);
}
