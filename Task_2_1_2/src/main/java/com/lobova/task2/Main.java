package com.lobova.task2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class where new Pizzeria is created.
 * There is no method to access the path, where the JSON file is (so far, unfortunately)
 * Manually you can change this. As well as the capacity of WareHouse and amount of orders.
 * There's no troubles to fix this? Let me know if it's better to make this fields part of a
 * PizzeriaConfigs class.
 * <p>
 * Also to stop Pizzeria after the last Pizza (id = amountOfOrders) will be delivered
 * user is supposed to write down "exit" to finish up the ExecutorServices.
 */
public class Main {
    public static void main(final String[] args) throws IOException, InterruptedException {

        final int storageCapacity = 10;
        final int amountOfOrders = 100;

        Orders orderList = new Orders();

        Pizzeria newYorkPizza = new Pizzeria(
                Paths.get("..\\MyPizzeria\\pizzeriaInfo.json"),
                storageCapacity,
                orderList);

        newYorkPizza.start();

        for (int i = 1; i < amountOfOrders; i++) {
            orderList.add(new Pizza(i));
            Thread.sleep(300);
        }

        System.out.println("====== All offers was put! Type \"exit\" to exit. ======");
        while (true) {
            String command = (new Scanner(System.in)).nextLine();
            if (command.equals("exit")) {
                newYorkPizza.stopWork();
                break;
            }
        }
        System.out.println("====== Exit. ======");
    }
}
