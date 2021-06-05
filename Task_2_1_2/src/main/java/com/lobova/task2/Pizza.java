package com.lobova.task2;

enum PizzaStatus {
    ORDERED,
    BAKED,
    WAITING_DELIVERY,
    DELIVERED
}

/**
 * The pizza-order.
 */
public class Pizza {
    final int id;
    PizzaStatus pizzaStatus;

    /**
     * @param id The number of the order.
     *           Initial status is ORDERED.
     */
    Pizza(final int id) {
        this.id = id;
        pizzaStatus = PizzaStatus.ORDERED;
    }
}

