package controllers;

import entities.Customer;
import models.CustomerManager;

public class CMController {
    private final CustomerManager customerManager;
    public CMController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    public CustomerManager getCustomerManager() {
        return customerManager;
    }
    public Customer findCustomerById(String id){
        return customerManager.findCustomerById(id);
    }
    public void registerCustomer(Customer customer){
        customerManager.addCustomer(customer);
    }
}
