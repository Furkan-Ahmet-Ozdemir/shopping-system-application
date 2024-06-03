package org.example.model;


import java.time.LocalDateTime;
import java.util.Set;

public class Order {

    private LocalDateTime createDate;
    private Set<Product> productList;
    private String orderCode; //-ordercode generate

    private Invoice invoice;

    public Order(Set<Product> productList, String orderCode) {
        this.createDate = LocalDateTime.now();
        this.productList = productList;
        this.orderCode = orderCode;
    }

    public Invoice getInvoice(){
        return invoice;
    }

    public void setInvoice(Invoice invoice){
        this.invoice = invoice;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }


    @Override
    public String toString() {
        return "Order{" +
                "createDate=" + createDate +
                ", productList=" + productList +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
