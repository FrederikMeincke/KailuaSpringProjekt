package com.example.kailuaspring.Controller;

import com.example.kailuaspring.Model.Car;
import com.example.kailuaspring.Model.Contract;
import com.example.kailuaspring.Model.Customer;
import com.example.kailuaspring.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String index(Model model){
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customerList",customerList);
        return "home/index";
    }

    //Customer
    @GetMapping("/showAllCustomers")
    public String showAllCustomers(Model model){
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customerList",customerList);
        return "home/showAllCustomers";
    }

    @PostMapping("/searchForCustomer")
    public String searchForCustomer(Model model, String search) {
        List<Customer> customerList = customerService.searchForCustomer(search);
        model.addAttribute("customerList", customerList);
        model.addAttribute(search);
        return "home/searchForCustomer";
    }

    @GetMapping("/addNewCustomer")
    public String addNewCustomer(){
        return "home/addNewCustomer";
    }

    @PostMapping("/addNewCustomer")
    public String addNewCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/showAllCustomers";
    }

    @GetMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.findCustomerByID(id);
        model.addAttribute("customer", customer);
        return "home/updateCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/showAllCustomers";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return "home/errorPage";
        } else {
            return "redirect:/showAllCustomers";
        }
    }

    //Contract
    @GetMapping("/showAllContracts")
    public String showAllContracts(Model model) {
        List<Contract> contractList = customerService.fetchAllContracts();
        model.addAttribute("contractList", contractList);
        return "home/showAllContracts";
    }

    @GetMapping("/showContracts/{id}")
    public String showContracts(@PathVariable("id") int id, Model model) {
        List<Contract> contractList = customerService.findContractsByCustomer(id);
        model.addAttribute("contractList", contractList);
        return "home/showAllContracts";
    }

    //Car
    @GetMapping("/showAllCars")
    public String showAllCars(Model model){
        List<Car> carList = customerService.fetchAllCars();
        model.addAttribute("carList", carList);
        return "home/showAllCars";
    }

    @GetMapping("updateCar/{id}")
    public String updateCar(@PathVariable("id") int id, Model model) {
        Car car = customerService.findCarByID(id);
        model.addAttribute("car", car);
        return "home/updateCar";
    }

    @PostMapping("updateCar/{id}")
    public String updateCar(@PathVariable("id") int id, @ModelAttribute Car car) {
        customerService.updateCar(id, car);
        return "redirect:/showAllCars";

    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        boolean deleted = customerService.deleteCar(id);
        if (deleted) {
            return "home/errorPage";
        } else {
            return "redirect:/showAllCars";
        }
    }


//    @GetMapping("showContract/{id}")
//    public String showContract(@PathVariable("id") int id) {
//        customerService.
//
//    }

}
