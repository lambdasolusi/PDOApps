package models;

import entities.Customer;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class CustomerManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 380115790950623999L;
    private List<Customer> customerList = new ArrayList<>();
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    public List<Customer> getCustomerList() {
        return customerList;
    }
    public Customer findCustomerById(String id){
        for(Customer customer : customerList){
            if(customer.getId().equals(id))
                return customer;
        }
        return null;
    }
}
