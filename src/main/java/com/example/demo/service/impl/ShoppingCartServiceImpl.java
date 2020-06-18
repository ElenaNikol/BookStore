package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.dto.ChargeRequest;
import com.example.demo.model.enumerations.CartStatus;
import com.example.demo.model.exception.*;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookService bookService;
    private final PaymentService paymentService;

    public ShoppingCartServiceImpl(UserService userService, ShoppingCartRepository shoppingCartRepository, BookService bookService, PaymentService paymentService) {
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.bookService = bookService;
        this.paymentService = paymentService;
    }

    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));

    }

    @Override
    public List<ShoppingCart> findAllByUsername(String userId) {
        return this.shoppingCartRepository.findAllByUserUsername(userId);
    }

    @Override
    public ShoppingCart createNewShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (this.shoppingCartRepository.existsByUserUsernameAndStatus(
                user.getUsername(),
                CartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlreadyCreated(userId);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    @Transactional
    public ShoppingCart addBookToShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Book book = this.bookService.findById(bookId);
        for (Book p : shoppingCart.getBooks()) {
            if (p.getId().equals(bookId)) {
                throw new BookAlreadyInShoppingCartException(book.getName());
            }
        }

        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String userId) {
       return this.shoppingCartRepository
               .findByUserUsernameAndStatus(userId,CartStatus.CREATED)
               .orElseGet(()-> {
                  ShoppingCart shoppingCart = new ShoppingCart();
                  User user = this.userService.findById(userId);
                  shoppingCart.setUser(user);
                  return this.shoppingCartRepository.save(shoppingCart);
               });
    }

    @Override
    public ShoppingCart removeBookFromShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        shoppingCart.setBooks(
                shoppingCart.getBooks()
                .stream()
                .filter(book -> !book.getId().equals(bookId))
                .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId,CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
        shoppingCart.setStatus(CartStatus.CANCELED);
        LocalDateTime localDateTime = LocalDateTime.now();
        shoppingCart.setClosedDate(localDateTime);
        return this.shoppingCartRepository.save(shoppingCart);
    }


    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId,CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
        List<Book> books = shoppingCart.getBooks();
        float price = 0;

        for (Book book : books) {
            if(book.getNumSamples() <= 0){
                throw new BookOutOfStockException(book.getName());
            }
            book.setNumSamples(book.getNumSamples()-1);
            price += book.getPrice();
        }
        Charge charge = null;
        try {
            charge = this.paymentService.pay(chargeRequest);
        } catch (CardException | StripeException e) {
            throw new TransactionFailedException(userId, e.getMessage());
        }


        shoppingCart.setBooks(books);
        shoppingCart.setClosedDate(LocalDateTime.now());
        shoppingCart.setStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
