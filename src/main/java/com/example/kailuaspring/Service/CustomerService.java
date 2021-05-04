package com.example.kailuaspring.Service;

import com.example.kailuaspring.Model.Contract;
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

    //todo update contract method
    public List<Contract> fetchAllContracts(){
        return customerRepo.fetchAllContracts();
    }

    public List<Contract> findContractsByCustomer(int id) {
        return customerRepo.searchForContractsByCustomer(id);

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

    public void updateCustomer(int id, Customer customer){
        customerRepo.updateCustomer(id, customer);
    }



}
