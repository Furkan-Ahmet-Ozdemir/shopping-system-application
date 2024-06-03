package org.example.repository;



import org.example.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerRepository {

    private List<Customer> customerList = new ArrayList<>();

    public void save(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }



}
