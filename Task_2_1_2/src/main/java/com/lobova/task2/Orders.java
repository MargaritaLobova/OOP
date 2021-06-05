package com.lobova.task2;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class for orders of the Pizzeria.
 */
public class Orders {

    /**
     * The Queue for orders.
     */
    private final LinkedBlockingQueue<Pizza> orders = new LinkedBlockingQueue<>();

    /**
     * @param newOrder the order to be stored into the order list.
     * @throws InterruptedException
     */
    public void add(final Pizza newOrder) throws InterruptedException {
        orders.put(newOrder);
        System.out.println("The order №" + newOrder.id + "was created.");
    }

    /**
     * @return Pizza object (FIFO model - the first in the Queue).
     * @throws InterruptedException
     */
    public Pizza take() throws InterruptedException {
        Pizza pizzaToBake = orders.take();
        System.out.println("The order №" + pizzaToBake.id + "is baking.");
        return pizzaToBake;
    }
}
