package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book product);
    void deleteById(Long id);
}
