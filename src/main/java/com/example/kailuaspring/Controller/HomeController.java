package com.example.kailuaspring.Controller;

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
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customerList",customerList);
        return "home/index";
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

    @GetMapping("/findCustomer/{id}")
    public String findCustomer(@PathVariable ("id") int id, Model model){
        model.addAttribute("customer", customerService.findCustomerByID(id));
        return "home/findCustomer";
    }
}
