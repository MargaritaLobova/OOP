package com.lobova.task2;

/**
 * Class that is describes delivery man and its working process.
 */
public class DeliveryMan implements Runnable {
    private final int id;
    private final long deliveringTime;
    private final Pizzeria workplace;

    /**
     * @param id             of the Delivery Man.
     * @param deliveringTime time that is needed to deliver order.
     * @param workplace      pizzeria where the delivering man is working.
     */
    DeliveryMan(final int id, final long deliveringTime, final Pizzeria workplace) {
        this.id = id;
        this.deliveringTime = deliveringTime;
        this.workplace = workplace;
    }

    /**
     * @return Pizza that was taken from the warehouse.
     * @throws InterruptedException
     */
    public Pizza takeFromWareHouse() throws InterruptedException {
        Pizza pizza = workplace.wareHouse.take();
        pizza.pizzaStatus = PizzaStatus.WAITING_DELIVERY;
        System.out.println("The pizza №" + pizza.id + "was taken by deliveryMan #" + id);
        return pizza;
    }

    /**
     * @param pizza to be delivered.
     * @throws InterruptedException
     */
    public void deliveryPizza(final Pizza pizza) throws InterruptedException {
        Thread.sleep(deliveringTime);
        pizza.pizzaStatus = PizzaStatus.DELIVERED;
        System.out.println("The pizza №" + pizza.id + "was delivered by deliveryMan #" + id);
    }

    /**
     * The working process of the delivery man - taking Pizza from the warehouse and deliver it.
     */
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
