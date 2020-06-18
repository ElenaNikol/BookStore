package com.example.demo.service;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.dto.ChargeRequest;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart findActiveShoppingCartByUsername(String userId);

    List<ShoppingCart> findAllByUsername(String userId);

    ShoppingCart createNewShoppingCart(String userId);

    ShoppingCart addBookToShoppingCart(String userId,
                                       Long bookId);

    ShoppingCart getActiveShoppingCart(String userId);


    ShoppingCart removeBookFromShoppingCart(String userId,
                                               Long bookId);

    ShoppingCart cancelActiveShoppingCart(String userId);

    ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest);

}
