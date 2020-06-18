package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
//    List<Book> findAll();
//    Optional<Book> findById(Long id);
//    Book save(Book book);
//    void deleteById(Long id);
    List<Book> findAllByCategoryId(@Param("id") Long id);

}
