package com.example.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class BookAlreadyInShoppingCartException extends RuntimeException{
    public BookAlreadyInShoppingCartException(String bookName) {
        super(String.format("Book %s is alraedy in active shopping cart", bookName));
    }
}
