package com.lobova.task2;

public class DeliveryMan implements Runnable {
    private final int id;
    private final long deliveringTime;
    private Pizzeria workplace;

    DeliveryMan(int id, long deliveringTime, Pizzeria workplace) {
        this.id = id;
        this.deliveringTime = deliveringTime;
        this.workplace = workplace;
    }

    public Pizza takeFromWareHouse() throws InterruptedException {
        Pizza pizza = workplace.wareHouse.take();
        pizza.pizzaStatus = PizzaStatus.WAITING_DELIVERY;
        System.out.println("The pizza №" + pizza.id + "was taken by deliveryMan #" + id);
        return pizza;
    }

    public void deliveryPizza(Pizza pizza) throws InterruptedException {
        Thread.sleep(deliveringTime);
        pizza.pizzaStatus = PizzaStatus.DELIVERED;
        System.out.println("The pizza №" + pizza.id + "was delivered by deliveryMan #" + id);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pizza pizza = takeFromWareHouse();
                deliveryPizza(pizza);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
