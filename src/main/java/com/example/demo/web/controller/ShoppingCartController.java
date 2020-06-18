package com.example.demo.web.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.service.AuthService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }


    @PostMapping("/{bookId}/add-book")
    public String addProductToShoppingCart(@PathVariable Long bookId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addBookToShoppingCart(this.authService.getCurrentUserId(), bookId);
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/books";
    }


    @PostMapping("/{bookId}/remove-book")
    public String removeProductToShoppingCart(@PathVariable Long bookId) {
        ShoppingCart shoppingCart = this.shoppingCartService.removeBookFromShoppingCart(this.authService.getCurrentUserId(), bookId);
        return "redirect:/books";
    }
}
