package com.lobova.task2;

public class Baker implements Runnable {

    private final int id;
    private final long bakingTimeMillis;
    private final Pizzeria workplace;

    public Baker(int id, long bakingTimeMillis, Pizzeria workplace) {
        this.id = id;
        this.bakingTimeMillis = bakingTimeMillis;
        this.workplace = workplace;
        System.out.println("Hello, I'm a baker " + id);
    }

    private void bakePizza(Pizza newOrder) throws InterruptedException {
        Thread.sleep(bakingTimeMillis);
        newOrder.pizzaStatus = PizzaStatus.BAKED;
        System.out.println("The order №" + newOrder.id + "was baked by baker #" + id);
    }

    // Todo fix wareHouse
    private void putToWareHouse(Pizza bakedPizza) throws InterruptedException {
        workplace.wareHouse.add(bakedPizza);
        System.out.println("The order №" + bakedPizza.id + "was put to wareHouse by baker #" + id);
    }

    @Override
    public void run() {

        if (workplace == null) {
            throw new IllegalStateException("Nowhere to work");
        }

        while (true) {
            Pizza newOrder;
            try {
                newOrder = workplace.orderList.take();
                bakePizza(newOrder);
                putToWareHouse(newOrder);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
