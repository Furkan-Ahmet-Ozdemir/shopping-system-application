package org.example;

import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args){

        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        orderService.getCustomerService().save("mehmet", "dırman", "mehmet@gmail.com", "sdfsdf", 26);
        orderService.getCustomerService().save("mehmet", "asd", "mehmet33@gmail.com", "asdasdsd", 29);

        orderService.getCustomerService().getCustomerList().forEach(System.out::println);

        Set<Product> productList2 = new HashSet<>();
        Product product1 = new Product("Don Kişot", new BigDecimal("134.00"), "kitap", 5);
        Product product2 = new Product("Monte Kristo Kontu", new BigDecimal("123.00"), "kitap", 10);
        Product product3 = new Product("S3 Mini", new BigDecimal("105.00"), "Telefon", 3);

        productList2.add(product1);
        productList2.add(product2);
        productList2.add(product3);

        Set<Product> productList3 = new HashSet<>();
        Product product4 = new Product("Ford Fiesta", new BigDecimal("1500.00"), "Araba", 5);
        Product product5 = new Product("Toyota Corolla", new BigDecimal("15000.00"), "Araba", 10);
        Product product6 = new Product("Iphone X", new BigDecimal("1500.00"), "Telefon", 3);

        productList3.add(product4);
        productList3.add(product5);
        productList3.add(product6);

        Customer customer1 = orderService.getCustomerService().getCustomerByEmail("mehmet@gmail.com").get();
        Customer customer2 = orderService.getCustomerService().getCustomerByEmail("mehmet33@gmail.com").get();


        orderService.save(productList3, customer2);

        // Bu kısımda manuel olarak Customer, Product, Order, Invoice  oluşturdum ve genel süreci yürüttüm

        Order order = new Order(productList2, "orderCode");
        System.out.println("Fatura: " + order.getInvoice());
        System.out.println("getProductList: " + order.getProductList());
        System.out.println("getCreateDate: " + order.getCreateDate());

        BigDecimal total = productList2.stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Invoice invoice = new Invoice(total);

        order.setInvoice(invoice);

        List<Order> orderList  = new ArrayList<>();
        orderList.add(order);

        customer1.setOrderList(orderList);






    }
}