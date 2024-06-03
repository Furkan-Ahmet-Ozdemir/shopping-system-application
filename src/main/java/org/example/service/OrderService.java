package org.example.service;




import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {

    CustomerService customerService  = new CustomerService();
    OrderRepository orderRepository = new OrderRepository();

    public List<Order> getOrderList() {
        return orderRepository.getOrderList();
    }


    public void save(Set<Product> productSet, Customer customer){
        Order order = new Order(productSet, generateOrderCode()); // hash olarak tutulsun.

        customer.getOrderList().add(order);

        orderRepository.save(order);

        BigDecimal earnedPoints = productSet.stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("total: " + earnedPoints);

        order.setInvoice(new Invoice(earnedPoints));
        System.out.println(  order.getInvoice().toString());
    }

    private String generateOrderCode() {
        Random random = new Random();
        String orderNumber;
        boolean isExistingNumber;

        do {
            int number = random.nextInt(999) + 10000000;  // 100 ile 999 arasında rastgele bir sayı oluşturur
            orderNumber = "KY" + number;

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
