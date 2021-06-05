package com.lobova.task2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

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
