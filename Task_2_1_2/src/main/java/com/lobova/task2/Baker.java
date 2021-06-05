package com.lobova.task2;

/**
 * Class that is describes baker and its working process.
 */
public class Baker implements Runnable {

    private final int id;
    private final long bakingTimeMillis;
    private final Pizzeria workplace;

    /**
     * @param id               of the baker.
     * @param bakingTimeMillis time that is needed to bake the pizza.
     * @param workplace        pizzeria where the baker is working.
     */
    public Baker(final int id, final long bakingTimeMillis, final Pizzeria workplace) {
        this.id = id;
        this.bakingTimeMillis = bakingTimeMillis;
        this.workplace = workplace;
        System.out.println("Hello, I'm a baker " + id);
    }

    /**
     * @param newOrder Pizza to be bake.
     * @throws InterruptedException
     */
    private void bakePizza(final Pizza newOrder) throws InterruptedException {
        Thread.sleep(bakingTimeMillis);
        newOrder.pizzaStatus = PizzaStatus.BAKED;
        System.out.println("The order №" + newOrder.id + "was baked by baker #" + id);
    }

    /**
     * @param bakedPizza to put it to the warehouse after baking.
     * @throws InterruptedException
     */
    private void putToWareHouse(final Pizza bakedPizza) throws InterruptedException {
        workplace.wareHouse.add(bakedPizza);
        System.out.println("The order №" + bakedPizza.id + "was put to wareHouse by baker #" + id);
    }

    /**
     * The working process of the baker - taking Pizza from the orderList and bake it.
     */
    @Override
    public void run() {
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
