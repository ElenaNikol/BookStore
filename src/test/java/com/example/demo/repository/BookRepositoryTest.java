package com.example.demo.repository;


import com.example.demo.model.Book;
import com.example.demo.model.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;

    @BeforeAll
    public void init() {
        Category c = new Category();
        c.setId(1l);
        c.setCategoryName("name1");
        c.setOpis("heyhey");
        c = categoryRepository.save(c);

        Book p1,p2,p3;
        p1 = new Book();
        p1.setName("nana");
        p1.setNumSamples(5);
        p1.setPrice(5);
        p1.setCategory(c);

        p2 = new Book();
        p2.setName("nana");
        p2.setNumSamples(2);
        p2.setPrice(2);
        p2.setCategory(c);

        p3 = new Book();
        p3.setName("nana");
        p3.setNumSamples(3);
        p3.setPrice(3);
        p3.setCategory(c);
        this.bookRepository.save(p1);
        this.bookRepository.save(p2);
        this.bookRepository.save(p3);
    }



    @Test
    void findAllByManufacturerId() {
        List<Book> products = this.bookRepository.findAllByCategoryId(1l);
        assert products.size() == 3;
    }


}
