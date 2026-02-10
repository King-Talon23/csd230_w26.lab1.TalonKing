package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String viewCart(Model model) {

        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setId(defaultCartId);
                    return cartRepository.save(newCart);
                });

        model.addAttribute("cart", cart);
        return "cartDetails.html";
    }

    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {

        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (cart != null && book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }

        return "redirect:/books";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {

        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (cart != null && book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }

        return "redirect:/cart";
    }
}
