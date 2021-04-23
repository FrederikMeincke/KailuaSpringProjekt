package com.example.kailuaspring.Repository;

import com.example.kailuaspring.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
        String sqlCustomer = "INSERT INTO customers (customers_id, customers_name, customers_mobile, customers_phone, customers_email, " +
                "customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate) " +
                "VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
        String sqlAddress = "INSERT INTO address (DEFAULT, address_street, address_number, address_floor) VALUES ();";
        String sqlZipCity = "INSERT INTO zipcity (zip_id, zip) VALUES (DEFAULT, ?)";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        String sqlLastAdded = "SELECT zip_id FROM kailua_cars.zipcity ORDER BY zip_id DESC limit 1;";

        SqlRowSet zip = jdbcTemplate.queryForRowSet(sqlLastAdded);
        zip.next();
        int zipInt = zip.getInt("zip_id");
        //TODO: Get the last zip_id added and create a new zip_id if the zip code doesn't exist already dito with address
        jdbcTemplate.update(sqlZipCity,customer.getZip());
        //jdbcTemplate.update(sqlAddress,);
        jdbcTemplate.update(sqlCustomer,customer.getCustomers_name(),customer.getCustomers_mobile(),customer.getCustomers_phone(),customer.getCustomers_email(),customer.getCustomers_drivers_license(),customer.getCustomers_drivers_license_issuedate(),customer.getCustomers_drivers_license_expiredate(),customer.getAddress_street(),customer.getAddress_number(),customer.getAddress_floor());
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
