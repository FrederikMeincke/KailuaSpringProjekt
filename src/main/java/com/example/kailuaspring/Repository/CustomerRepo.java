package com.example.kailuaspring.Repository;

import com.example.kailuaspring.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@Repository

public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> fetchAll(){
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    public Customer addCustomer(Customer customer){
        return null;
    }

    public Customer findCustomerByID(int id){
        return null;
    }

    public Boolean deleteCustomer(int id){
        return null;
    }

    public Customer updateCustomer(int id, Customer customer){
        return null;
    }

}
