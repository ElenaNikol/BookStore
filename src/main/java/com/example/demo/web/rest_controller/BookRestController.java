package com.example.demo.web.rest_controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return this.bookService.findById(id);
    }

//    @GetMapping("/by-manufacturer/{manufacturerId}")
//    public List<Book> findAllByManufacturerId(@PathVariable Long manufacturerId) {
//        return this.bookService.(manufacturerId);
//    }

    @PostMapping
    public Book save(@Valid Book book) throws IOException {
        return this.bookService.saveBook(book);
    }


//    @PutMapping("/{id}")
//    public Book update(@PathVariable Long id,
//                          @Valid  Book book) throws IOException {
//        return this.bookService.updateBook(id, book);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.bookService.deleteById(id);
    }

}
