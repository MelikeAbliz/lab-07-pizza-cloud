package com.cydeo.repository;

import com.cydeo.exception.PizzaNotFoundException;
import com.cydeo.model.Pizza;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class PizzaRepository {

    private static List<Pizza> pizzaList = new ArrayList<>();

    public Pizza createPizza(Pizza pizzaToSave) {
        pizzaList.add(pizzaToSave);
        return pizzaToSave;
    }

    public List<Pizza> readAll() {
        return pizzaList;
    }
    public Pizza findPizzaById(UUID uuid) {
        return readAll().stream().filter(p->p.getId().equals(uuid))
                .findFirst().orElseThrow(()->new PizzaNotFoundException("pizza not found"));
    }

}
