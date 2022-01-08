package entities;

import models.CustomerManager;
import models.OrderManager;
import models.OutletManager;
import models.ProductManager;

import java.io.Serial;
import java.io.Serializable;

public class Shop implements Serializable {
    @Serial
    private static final long serialVersionUID = -376517514755110871L;
    private String id, name, address;
    private Location location;
    private ProductManager productManager = new ProductManager();
    private OrderManager orderManager = new OrderManager();
    private OutletManager outletManager = new OutletManager();
    private CustomerManager customerManager = new CustomerManager();

    public Shop() {
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }
    public OutletManager getOutletManager() {
        return outletManager;
    }
    public void setOutletManager(OutletManager outletManager) {
        this.outletManager = outletManager;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public ProductManager getProductManager() {
        return productManager;
    }
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
    public OrderManager getOrderManager() {
        return orderManager;
    }
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }
}
