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
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){

        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        orderService.getCustomerService().save("mehmet", "dırman", "mehmet@gmail.com", "sdfsdf", 26);
        orderService.getCustomerService().save("mehmet", "asd", "mehmet33@gmail.com", "asdasdsd", 29);

        orderService.getCustomerService().getCustomerList().forEach(System.out::println);

        Set<Product> productList2 = new HashSet<>();
        Product product1 = new Product("Don Kişot", new BigDecimal("134.00"), "Kitap", 5);
        Product product2 = new Product("Monte Kristo Kontu", new BigDecimal("123.00"), "Kitap", 10);
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

//        //Tüm müşterileri listeleyin
        System.out.println("Tüm müşteriler: " + orderService.getCustomerService().getCustomerList());

        //Yeni Müşteri oluşturabilen
        System.out.println("Yeni Müşteri :" );

        orderService.getCustomerService().save("can", "dırman", "can@gmail.com", "sdfsdf", 26);

//
//        //İçerisinde ‘C’ harfi olan müşterileri listeleyin
        System.out.println("İçerisinde ‘C’ harfi olan müşterileri listeleyin: " + orderService.getCustomerService().getCustomerList().stream()
                        .map(customer -> customer.getName().toLowerCase())
                        .filter(s -> s.contains("c"))
                        .collect(Collectors.toList()));

//        Sistemdeki tüm faturaları listeleyin
        System.out.println("Sistemdeki tüm faturaları listeleyin" + orderService.getCustomerService().getCustomerList().stream()
                .flatMap(customer -> customer.getOrderList().stream())
                .map(Order::getInvoice)
                .toList());

//        Sistemdeki 1500TL üstündeki faturaları listeleyin
        System.out.println("Sistemdeki 1500TL üstündeki faturaları: " + orderService.getCustomerService().getCustomerList().stream()
                .flatMap(customer -> customer.getOrderList().stream())
                .map(Order::getInvoice)
                .filter(invoice2 -> invoice2.getTotal().compareTo(new BigDecimal("1500.00")) > 0).toList());

//        Sistemdeki 1500TL üstündeki faturaları ortalamasını hesaplayın
        BigDecimal avg = BigDecimal.valueOf(orderService.getCustomerService().getCustomerList().stream()
                .flatMap(customer -> customer.getOrderList().stream())
                .map(Order::getInvoice)
                .map(Invoice::getTotal)
                .filter(invoice2Total -> invoice2Total.compareTo(new BigDecimal("1500.00")) > 0)
                .collect(Collectors.averagingDouble(BigDecimal::doubleValue)));

        System.out.println("1500TL üstündeki faturaları ortalaması: " + avg);

//        //Sistemdeki 500TL altındaki faturalara sahip müşterilerin isimleri listeleyin
        List<String> names = orderService.getCustomerService().getCustomerList().stream()
                .filter(customer -> customer.getOrderList().stream()
                        .map(Order::getInvoice)
                        .anyMatch(invoice2 -> invoice2.getTotal().compareTo(new BigDecimal("500.00")) < 0))
                .map(Customer::getName)
                .toList();

        System.out.println("Sistemdeki 500TL altındaki faturalara sahip müşterilerin isimleri: " + names);

//        //Haziran ayını faturalarını ortalaması 750 altı olan firmalarının hangi sektörde(category) olduğunu listeleyen kodu yazın.
        List<String> sectors = orderService.getCustomerService().getCustomerList().stream()
                .flatMap(customer -> customer.getOrderList().stream())
                .filter(order1 -> order1.getCreateDate().getMonthValue() == 6)
                .collect(Collectors.groupingBy(
                        order2 -> order2.getProductList().stream()
                                .map(Product::getCategory)
                                .distinct()
                                .collect(Collectors.toList()),
                        Collectors.averagingDouble(order3 -> order3.getInvoice().getTotal().doubleValue())
                )).entrySet().stream()
                .filter(entry -> entry.getValue() < 750.00)
                .flatMap(entry -> entry.getKey().stream())
                .distinct()
                .toList();

        System.out.println("Haziran ayını faturalarını ortalaması 750 altı olan firmalarının hangi sektörde: " + sectors);

        // Haziran ayında kayıt olan müşterilerin faturalarının toplam tutarını listeleyin
        BigDecimal totalJuneInvoices = orderService.getCustomerService().getCustomerList().stream()
                .filter(customer -> customer.getCreatedDate().getMonthValue() == 6)
                .flatMap(customer -> customer.getOrderList().stream())
                .map(Order::getInvoice)
                .map(Invoice::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Haziran ayında kayıt olan müşterilerin faturalarının toplam tutarı: " + totalJuneInvoices);
    }
}