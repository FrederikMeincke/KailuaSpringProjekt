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
        //String sql = "SELECT * FROM customers";
        String sql = "SELECT customers_id, customers_name, customers_mobile, customers_phone, customers_email, customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate, address_street, address_number, address_floor, zip, city, country FROM kailua_cars.customers inner join address on address_id=customers_address inner join zipcity on address_zip = zip_id;";
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
