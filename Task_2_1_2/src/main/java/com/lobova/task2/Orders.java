package com.lobova.task2;

import java.util.concurrent.LinkedBlockingQueue;

public class Orders {

    private final LinkedBlockingQueue<Pizza> orders = new LinkedBlockingQueue<>();

    public void add(Pizza newOrder) throws InterruptedException {
        orders.put(newOrder);
        System.out.println("The order №" + newOrder.id + "was created.");
    }

    public Pizza take() throws InterruptedException {
        Pizza pizzaToBake = orders.take();
        System.out.println("The order №" + pizzaToBake.id + "is baking.");
        return pizzaToBake;
    }
}