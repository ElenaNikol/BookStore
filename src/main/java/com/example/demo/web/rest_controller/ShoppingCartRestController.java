package com.example.demo.web.rest_controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.service.AuthService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService,
                                      AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public List<ShoppingCart> findAllbyUsername(){
        return this.shoppingCartService.findAllByUsername(this.authService.getCurrentUserId());
    }

    @PostMapping
    public ShoppingCart createNewShoppingCart() {
        return this.shoppingCartService.createNewShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/{bookId}/books")
    public ShoppingCart addBookToCart(@PathVariable Long bookId) {
        return this.shoppingCartService.addBookToShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }
    @DeleteMapping("/{bookId}/books")
    public ShoppingCart removeProductFromCart(@PathVariable Long bookId) {
        return this.shoppingCartService.removeBookFromShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }

    @PatchMapping("/cancel")
    public ShoppingCart cancelActiveShoppingCart() {
        return this.shoppingCartService.cancelActiveShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/checkout")
    public ShoppingCart checkoutActiveShoppingCart() {
       return null;
    }



}

