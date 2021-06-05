package com.lobova.task2;

enum PizzaStatus {
    ORDERED,
    BAKED,
    WAITING_DELIVERY,
    DELIVERED
}

public class Pizza {
    int id;
    PizzaStatus pizzaStatus;

    Pizza(int id) {
        this.id = id;
        pizzaStatus = PizzaStatus.ORDERED;
    }
}
