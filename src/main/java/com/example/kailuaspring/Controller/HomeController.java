package com.example.kailuaspring.Controller;

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

    @GetMapping("/addNewCustomer")
    public String addNewCustomer(){
        return "home/addNewCustomer";
    }

    @PostMapping("/addNewCustomer")
    public String addNewCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/";
    }

//    @GetMapping("/findCustomer/{id}")
//    public String findCustomer(@PathVariable ("id") int id, Model model){
//        model.addAttribute("customer", customerService.findCustomerByID(id));
//        return "home/findCustomer";
//    }


    @PostMapping("/searchForCustomer")
    public String searchForCustomer(Model model, String search) {
        List<Customer> customerList = customerService.searchForCustomer(search);
        model.addAttribute("customerList", customerList);
        model.addAttribute(search);
        return "home/searchForCustomer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return "home/errorPage";
        } else {
            return "redirect:/";
        }
    }

//    @GetMapping("showContract/{id}")
//    public String showContract(@PathVariable("id") int id) {
//        customerService.
//
//    }
}
