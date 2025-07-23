package com.dwwm.visiotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndUserId(String name, Long userId);
}
