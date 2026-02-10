package csd230.lab1.controllers;

import csd230.lab1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        model.addAttribute("order",
                orderRepository.findById(id).orElse(null));
        return "orderDetails";
    }
}
