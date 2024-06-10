package org.example.service;




import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {

    private final CustomerService customerService  = new CustomerService();
    private final OrderRepository orderRepository = new OrderRepository();

    public List<Order> getOrderList() {
        return orderRepository.getOrderList();
    }


    public void save(Set<Product> productSet, Customer customer){
        Order order = new Order(productSet, generateOrderCode());

        customer.getOrderList().add(order);

        orderRepository.save(order);

        BigDecimal total = productSet.stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("total: " + total);

        order.setInvoice(new Invoice(total));
        System.out.println(  order.getInvoice().toString());
    }

    private String generateOrderCode(){
        LocalTime localTime = LocalTime.now();
        String orderNumber;
        boolean isExistingNumber;

        do {
            orderNumber = "KY" + localTime.getHour()*10 + localTime.getMinute()*9 + localTime.getSecond()*8 ;

            String finalOrderNumber = orderNumber;
            isExistingNumber = customerService.getCustomerList().stream()
                    .filter(customer -> customer.getOrderList() != null)
                    .flatMap(customer -> customer.getOrderList().stream())
                    .anyMatch(order -> Objects.equals(order.getOrderCode(), finalOrderNumber));
        } while (isExistingNumber);

        return orderNumber;
    }

    public Set<Order> listOrdersByName(String name) {

        return customerService.getCustomerList().stream()
                .filter(customer -> customer.getName().equals(name))
                .flatMap(customer -> customer.getOrderList().stream())
                .collect(Collectors.toSet());
    }

    public CustomerService getCustomerService(){
        return customerService;
    }

}
