package com.cydeo.controller;

import com.cydeo.model.Pizza;
import com.cydeo.model.PizzaOrder;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId, @ModelAttribute PizzaOrder pizzaOrder) {

        pizzaOrder.setPizza(getPizza(pizzaId));
        System.out.println("Order is successfully saved");// Saved the order

        return "redirect:/home";
    }

    private Pizza getPizza(UUID pizzaId) {
        return pizzaRepository.findPizzaById(pizzaId);
    }

}
