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
        boolean emptyField =
                customer.getCustomers_name().isEmpty() || customer.getCustomers_mobile().isEmpty() ||
                        customer.getCustomers_phone().isEmpty() || customer.getCustomers_email().isEmpty() ||
                        customer.getCustomers_drivers_license().isEmpty() || customer.getCustomers_drivers_license_issuedate().isEmpty() ||
                        customer.getCustomers_drivers_license_expiredate().isEmpty() || customer.getAddress_street().isEmpty() ||
                        customer.getAddress_number().isEmpty() || customer.getAddress_floor().isEmpty() ||
                        customer.getCity().isEmpty() || customer.getZip() == 0;
        if(emptyField) {
            return "home/error/errorPage";
        }
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

    @GetMapping("/addNewContract")
    public String addNewContract() {
        return "home/addNewContract";
    }

    @PostMapping("/addNewContract")
    public String addNewContract(@ModelAttribute Contract contract) {

        boolean emptyField =
                contract.getContracts_customer() == 0 ||
                        contract.getContracts_fromdate().isEmpty() || contract.getContracts_todate().isEmpty() ||
                        contract.getContracts_maxkm().isEmpty() || contract.getContracts_startkm().isEmpty() ||
                        contract.getContracts_cars_id() == 0;
        if(emptyField) {
            return "home/error/errorPage";
        }
        customerService.addNewContract(contract);
        return "redirect:/showAllContracts";
    }

    @GetMapping("/updateContract/{id}")
    public String updateContract(@PathVariable("id") int id, Model model) {
        Contract contract = customerService.findContractByID(id);
        model.addAttribute(contract);
        return "home/updateContract";
    }

    @PostMapping("/updateContract/{id}")
    public String updateContract(@PathVariable("id") int id, @ModelAttribute Contract contract) {
        customerService.updateContract(id, contract);
        return "redirect:/showAllContracts";
    }

    @GetMapping("/deleteContract/{id}")
    public String deleteContract(@PathVariable("id") int id) {
        boolean deleted = customerService.deleteContract(id);
        if (deleted) {
            return "home/errorPage";
        } else {
            return "redirect:/showAllContracts";
        }
    }

    //Car
    @GetMapping("/showAllCars")
    public String showAllCars(Model model){
        List<Car> carList = customerService.fetchAllCars();
        model.addAttribute("carList", carList);
        return "home/showAllCars";
    }

    @GetMapping("/addNewCar")
    public String addNewCar() {
        return "home/addNewCar";
    }

    @PostMapping("/addNewCar")
    public String addNewCar(@ModelAttribute Car car) {
        boolean emptyField = car.getCar_brand().isEmpty() || car.getCar_models_name().isEmpty() || car.getCar_models_name().isEmpty()
                || car.getCar_models_fueltype().isEmpty() || car.getCars_type().isEmpty() || car.getCars_license_plate().isEmpty()
                || car.getCars_first_reg().isEmpty() || car.getCars_current_km().isEmpty() || car.getCars_model_id() == 0
                || car.getCar_models_brand_id() == 0;

        if(emptyField) {
            return "home/error/errorPage";
        }
        customerService.addNewCar(car);
        return "redirect:/showAllCars";
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
