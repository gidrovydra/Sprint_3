package order;

import model.Order;

import java.util.List;

public class StepsOrder {

    //шаг "Создать конкретный заказ"
    public Order createExampleOrder(List<String> color) {
        Order order = new Order();

        order.setFirstName("Ivan");
        order.setLastName("Petrov");
        order.setAddress("Moscow, Lenina 85");
        order.setMetroStation("5");
        order.setPhone("+7 854 359 45 45");
        order.setRentTime(6);
        order.setDeliveryDate("2022-05-02");
        order.setComment("It`s comment for my order");
        order.setColor(color);

        return order;
    }
}
