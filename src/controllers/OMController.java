package controllers;

import models.Order;
import models.OrderManager;

import java.util.ArrayList;
import java.util.List;

public class OMController {
    private OrderManager orderManager;

    public OMController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }
    public Order findOrderByInvoice(String invoice){
        return orderManager.findOrderById(invoice);
    }
}
