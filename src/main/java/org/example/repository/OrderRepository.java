package org.example.repository;



import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderRepository {

    private List<Order> orderList = new ArrayList<>();

    public void save(Order order) {
        orderList.add(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
