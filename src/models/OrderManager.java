package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 2808529040800590284L;
    private OrderManager orderManager;
    private final List<Order> orderList = new ArrayList<>();
    public OrderManager() {
        this.orderManager = orderManager;
    }
    public List<Order> getOrderList() {
        return orderList;
    }
    public void addOrder(Order order){
        orderList.add(order);
    }
    public Order findOrderById(String invoice){
        for(Order order : orderList){
            if(order.getInvoice().equals(invoice))
                return order;
        }
        return null;
    }
}
