package com.lobova.task2;

import java.util.concurrent.LinkedBlockingQueue;

public class WareHouse {

    private final LinkedBlockingQueue<Pizza> wareHouse = new LinkedBlockingQueue<>();
    private int wareHouseCapacity;

    WareHouse(int wareHouseCapacity) {
        this.wareHouseCapacity = wareHouseCapacity;
    }

    void add(Pizza newOrder) throws InterruptedException {
        wareHouse.put(newOrder);
        System.out.println("The order №" + newOrder.id + " was added in wareHouse");
    }

    Pizza take() throws InterruptedException {
        Pizza pizzaToBake = wareHouse.take();
        System.out.println("The order №" + pizzaToBake.id + " was taken from wareHouse.");
        return pizzaToBake;
    }
}
