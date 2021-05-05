package com.example.kailuaspring.Service;

import com.example.kailuaspring.Model.Car;
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

    //Customer
    public List<Customer> fetchAllCustomers(){
        return customerRepo.fetchAllCustomers();
    }

    public Customer findCustomerByID(int id){
        return customerRepo.findCustomerByID(id);
    }

    public List<Customer> searchForCustomer(String name){
        return customerRepo.searchForCustomer(name);
    }

    public void addCustomer(Customer customer){
        customerRepo.addCustomer(customer);
    }

    public void updateCustomer(int id, Customer customer){
        customerRepo.updateCustomer(id, customer);
    }

    public boolean deleteCustomer(int id){
        return customerRepo.deleteCustomer(id);
    }

    //Contracts

    //todo update contract method
    public List<Contract> fetchAllContracts(){
        return customerRepo.fetchAllContracts();
    }

    public List<Contract> findContractsByCustomer(int id) {
        return customerRepo.searchForContractsByCustomer(id);

    }

    //Cars
    public List<Car> fetchAllCars() {
        return customerRepo.fetchAllCars();
    }

    public Car findCarByID(int id){
        return customerRepo.findCarByID(id);
    }

    public void addCar(Car car){
        customerRepo.addCar(car);
    }

    public void updateCar(int id, Car car){
        customerRepo.updateCar(id, car);
    }

    public boolean deleteCar(int id) {
        return customerRepo.deleteCar(id);
    }



}
