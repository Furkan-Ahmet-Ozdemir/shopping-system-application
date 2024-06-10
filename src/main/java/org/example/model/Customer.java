package org.example.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Customer {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer credit;
    private String phoneNumber;
    private Boolean isActive;

    private LocalDate createdDate;

    private int age;
    private List<Order> orderList = new ArrayList<>();

    private Customer(){

    }


    public Customer(String name, String surname, String email, String password,int age){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isActive = true;
        this.age = age;
        this.createdDate=LocalDate.now();
    }

    public Customer(String name, String surname, String email, String password, Integer credit,
                    String phoneNumber, List<Order> orderList){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.credit = credit;
        this.phoneNumber = phoneNumber;
        this.orderList = orderList;
        this.isActive = true;
        this.createdDate=LocalDate.now();
    }

    public LocalDate getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getEmail(){
        return email;
    }

    public Boolean getActive(){
        return isActive;
    }

    public void setActive(Boolean active){
        isActive = active;
    }

    public List<Order> getOrderList(){
        if(orderList == null) {
            return new ArrayList<>();
        }
        return orderList;
    }

    public void setOrderList(List<Order> orderList){
        this.orderList = orderList;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                '}';
    }

}
