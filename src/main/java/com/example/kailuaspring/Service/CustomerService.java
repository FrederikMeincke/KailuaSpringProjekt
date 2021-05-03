package com.example.kailuaspring.Service;

import com.example.kailuaspring.Model.Customer;
import com.example.kailuaspring.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> fetchAllCustomers(){
        return customerRepo.fetchAllCustomers();
    }

    public List<Customer> fetchAllContracts(){
        return customerRepo.fetchAllContracts();
    }

    public Customer addCustomer(Customer customer){
        return customerRepo.addCustomer(customer);
    }

    public Customer findCustomerByID(int id){
        return customerRepo.findCustomerByID(id);
    }

    public List<Customer> searchForCustomer(String name){
        return customerRepo.searchForCustomer(name);
    }

    public Boolean deleteCustomer(int id){
        return customerRepo.deleteCustomer(id);
    }

    public Customer updateCustomer(int id, Customer customer){
        return customerRepo.updateCustomer(id,customer);
    }

    //public Contract searchForContract//todo here

}
