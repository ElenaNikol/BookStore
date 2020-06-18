package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!in-memory")
public interface CategoryRepository extends JpaRepository<Category,Long> {
//    List<Category> findAll();
//    Optional<Category> findById(Long id);
//    Category save(Category category);
//    void deleteById(Long id);
}
