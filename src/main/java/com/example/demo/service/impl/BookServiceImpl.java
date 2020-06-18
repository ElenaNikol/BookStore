package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.exception.BookNotFoundException;
import com.example.demo.model.exception.BookAlreadyInShoppingCartException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class
BookServiceImpl  implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }


    @Override
    public Book updateBook(Long id, Book book) {
        Book b = this.findById(id);
        Category category = this.categoryService.findById(book.getCategory().getId());
        b.setCategory(category);
        b.setPrice(book.getPrice());
        b.setNumSamples(book.getNumSamples());
        b.setImage(book.getImage());
        return this.bookRepository.save(b);

    }

    @Override
    public Book saveBook(Book book) {
        Category category = this.categoryService.findById(book.getCategory().getId());
        book.setCategory(category);
        return this.bookRepository.save(book);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = this.findById(id);
        if(book.getShoppingCarts().size() > 0){
            throw new BookAlreadyInShoppingCartException(book.getName());
        }
        this.bookRepository.deleteById(id);
    }
}
