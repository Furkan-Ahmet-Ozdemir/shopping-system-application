package org.example.service;



import org.example.exception.EmailExistsException;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepository();

    public void save(String name, String surname, String email, String password, int age) {

        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            throw new EmailExistsException("Bu email ile kayıtlı bir kullanıcı var!!!");
        }

        int hash = Objects.hash(password);
        password = String.valueOf(hash);
        Customer customer1 = new Customer(name, surname, email,password ,age); // hash olarak tutulsun.
        customerRepository.save(customer1);

    }

    public List<Customer> getCustomerList() {
        return customerRepository.getCustomerList();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.getCustomerList().stream().filter( customer -> customer.getEmail().equals(email)).findFirst();
    }



}
