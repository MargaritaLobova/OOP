package com.lobova.task2;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class for Pizzeria creation.
 */
public class Pizzeria {
    // the number of bakers and delivery men to be hired
    public int numBakers;
    public int numDeliveryMen;

    //storages - order list and already baked pizza list
    final Orders orderList;
    final WareHouse wareHouse;

    //all employees - bakers and delivery men
    final List<Baker> bakers;
    final List<DeliveryMan> deliveryMen;

    //executor services for bakers and delivery men
    public ExecutorService bakerExecutor;
    public ExecutorService deliveryManExecutor;

    // TODO Maybe these also better to put into PizzeriaConfigs in future(?)
    private static final long BAKING_TIME_MILLIS = 1000;
    private static final long DELIVERY_TIME_MILLIS = 1500;

    /**
     * @param path              path to the JSON file where Pizzeria Configs are
     * @param wareHouseCapacity the capacity of the storage
     * @param orderList         the Queue of all the orders
     * @throws IOException
     */
    public Pizzeria(final Path path, final int wareHouseCapacity, final Orders orderList) throws IOException {

        String jsonString = new String(Files.readAllBytes(path));
        Gson gson = new Gson();
        PizzeriaConfigs configs = gson.fromJson(jsonString, PizzeriaConfigs.class);

        this.numBakers = configs.numBakers;
        this.numDeliveryMen = configs.numDeliveryMen;

        System.out.println("Pizzeria started"
                + "\nInitial bakers count: "
                + this.numBakers
                + "\nInitial couriers count: "
                + this.numDeliveryMen);

        this.orderList = orderList;
        this.wareHouse = new WareHouse(wareHouseCapacity);

        bakers = new ArrayList<>(numBakers);
        deliveryMen = new ArrayList<>(numDeliveryMen);

        for (int i = 0; i < numBakers; i++) {
            bakers.add(new Baker(i, BAKING_TIME_MILLIS, this));
        }
        for (int i = 0; i < numDeliveryMen; i++) {
            deliveryMen.add(new DeliveryMan(i, DELIVERY_TIME_MILLIS, this));
        }
    }

    /**
     * Starting the Executors of bakers and delivery men
     */
    public void start() {
        bakerExecutor = Executors.newFixedThreadPool(bakers.size());
        bakers.forEach(bakerExecutor::execute);
        deliveryManExecutor = Executors.newFixedThreadPool(deliveryMen.size());
        deliveryMen.forEach(deliveryManExecutor::execute);
    }

    /**
     * Finishing the Executors
     */
    public void stopWork() {
        bakerExecutor.shutdownNow();
        deliveryManExecutor.shutdownNow();
    }

}
