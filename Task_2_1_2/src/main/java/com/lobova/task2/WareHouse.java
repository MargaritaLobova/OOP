package com.lobova.task2;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class for storing and taking pizzas which were already baked by bakers.
 */
public class WareHouse {

    /**
     * The Queue for pizzas to be stored correctly.
     * WareHouseCapacity to know the limits of the storage.
     */
    private final LinkedBlockingQueue<Pizza> wareHouse = new LinkedBlockingQueue<>();
    private final int wareHouseCapacity;

    /**
     * @param wareHouseCapacity the capacity of the warehouse to be created.
     */
    WareHouse(final int wareHouseCapacity) {
        this.wareHouseCapacity = wareHouseCapacity;
    }

    /**
     * @param newOrder the already baked Pizza to be stored into the warehouse.
     * @throws InterruptedException
     */
    void add(final Pizza newOrder) throws InterruptedException {
        wareHouse.put(newOrder);
        System.out.println("The order №" + newOrder.id + " was added in wareHouse");
    }

    /**
     * @return Pizza object (FIFO model - the first in the Queue).
     * @throws InterruptedException
     */
    Pizza take() throws InterruptedException {
        Pizza pizzaToBake = wareHouse.take();
        System.out.println("The order №" + pizzaToBake.id + " was taken from wareHouse.");
        return pizzaToBake;
    }
}
