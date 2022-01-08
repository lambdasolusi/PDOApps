package models;

import apps.Apps;
import entities.Customer;
import entities.Outlet;
import enums.OrderStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 5550599847706971022L;
    private List<SaleLineItem> items = new ArrayList<>();
    private String invoice;
    private LocalDateTime now;
    private Customer customer;
    private double total = 0.0;
    private Outlet outlet;
    private OrderStatus orderStatus = OrderStatus.RECEIVED;
    public void addItem(SaleLineItem item){
        items.add(item); total += item.getSubTotal();
    }
    public Order() {
        setNow(LocalDateTime.now());
    }
    public Outlet getOutlet() {
        return outlet;
    }
    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void checkOut() {
        setNow(LocalDateTime.now());
        setOutlet(Apps.getShop().getOutletManager().getOutletList().get(0));
    }
    public List<SaleLineItem> getItems() {
        return items;
    }
    public void setItems(List<SaleLineItem> items) {
        this.items = items;
    }
    public String getInvoice() {
        return invoice;
    }
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
    public LocalDateTime getNow() {
        return now;
    }
    public void setNow(LocalDateTime now) {
        this.now = now;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", invoice='" + invoice + '\'' +
                ", date=" + now +
                '}';
    }
    public double getTotal() {
        total = 0.0;
        for(SaleLineItem item : items)
            total += item.getSubTotal();
        return total;
    }
}
