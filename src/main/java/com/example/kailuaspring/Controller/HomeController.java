package com.example.kailuaspring.Controller;

import com.example.kailuaspring.Model.Customer;
import com.example.kailuaspring.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String index(Model model){
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers",customerList);
        return "home/index";
    }
}
