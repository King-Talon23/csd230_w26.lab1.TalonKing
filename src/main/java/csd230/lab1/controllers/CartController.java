package csd230.lab1.controllers;

import csd230.lab1.repositories.*;
import csd230.lab1.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private CartEntity getCartForCurrentUser(Principal principal) {
        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username);

        CartEntity cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        return cart;
    }

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        model.addAttribute("cart", cart);
        return "cartDetails";
    }

    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }

        return "redirect:/books";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }

        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);

        if (cart.getProducts().isEmpty()) {
            return "redirect:/cart";
        }

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double total = 0;

        for (ProductEntity product : cart.getProducts()) {
            total += product.getPrice();

            if (product instanceof PublicationEntity pub) {
                if (pub.getCopies() > 0) {
                    pub.setCopies(pub.getCopies() - 1);
                }
            }

            order.getProducts().add(product);
        }

        order.setTotalAmount(total);
        orderRepository.save(order);

        cart.getProducts().clear();
        cartRepository.save(cart);

        return "redirect:/orders/" + order.getId();
    }
}
