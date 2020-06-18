package com.example.demo.web.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.exception.BookAlreadyInShoppingCartException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorRepository authorRepository;

    public BooksController(BookService bookService, CategoryService categoryService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String getProductPage(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }



    @GetMapping("/new")
    public String addNewBookPage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", this.authorRepository.findAll());
        model.addAttribute("book",new Book() );
        return "add-book";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable Long id){
        try {
            Book book = this.bookService.findById(id);
            List<Category> categories =  this.categoryService.findAll();
            model.addAttribute("book",book);
            model.addAttribute("categories",categories);
            model.addAttribute("authors", this.authorRepository.findAll());
            return "add-book";
        }catch (RuntimeException ex){
            return "redirect:/books?error=" + ex.getMessage();
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public String saveBook(@Valid Book book, BindingResult bindingResult ,Model model
    ) { if(bindingResult.hasErrors()){
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", this.authorRepository.findAll());
        return "add-book";
    }

            this.bookService.saveBook(book);


        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookWithPost(@PathVariable Long id){
        try {
            this.bookService.deleteById(id);
        } catch (BookAlreadyInShoppingCartException ex) {
            return String.format("redirect:/books?error=%s", ex.getMessage());
        }

        return "redirect:/books";
    }
}
