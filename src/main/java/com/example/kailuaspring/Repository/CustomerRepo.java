package com.example.kailuaspring.Repository;

import com.example.kailuaspring.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.RowSet;
import java.util.List;

@Repository

public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * @return
     */
    public List<Customer> fetchAll(){
        //String sql = "SELECT * FROM customers";
        String sql = "SELECT customers_id, customers_name, customers_mobile, customers_phone, customers_email, customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate, address_street, address_number, address_floor, zip, city, country FROM kailua_cars.customers inner join address on address_id = customers_address inner join zipcity on address_zip = zip_id;";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    /**
     *
     * @param customer
     * @return
     */
    public Customer addCustomer(Customer customer){
        String sqlZipCity = "INSERT INTO zipcity (zip_id, zip, city, country) VALUES (DEFAULT, ?, ?, 'Denmark')";

        jdbcTemplate.update(sqlZipCity, customer.getZip(), customer.getCity());
        String sqlLastAddedZip = "SELECT zip_id FROM kailua_cars.zipcity ORDER BY zip_id DESC limit 1;";
        SqlRowSet zip = jdbcTemplate.queryForRowSet(sqlLastAddedZip);
        zip.next();
        int zipInt = zip.getInt("zip_id");

        String sqlAddress = "INSERT INTO address (address_id, address_zip, address_street, address_number, address_floor) VALUES (DEFAULT," + zipInt + ",?,?,?);";

        jdbcTemplate.update(sqlAddress,customer.getAddress_street(),customer.getAddress_number(),customer.getAddress_floor());
        String sqlLastAddedAddress = "SELECT address_id FROM kailua_cars.address ORDER BY address_id DESC limit 1;";
        SqlRowSet adr = jdbcTemplate.queryForRowSet(sqlLastAddedAddress);
        adr.next();
        int addressInt = adr.getInt("address_id");

        String sqlCustomer = "INSERT INTO customers (customers_id, customers_address ,customers_name, customers_mobile, customers_phone, customers_email, " +
                "customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate) " +
                "VALUES (DEFAULT," + addressInt + ",?,?,?,?,?,?,?)";

        //RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);



        jdbcTemplate.update(sqlCustomer,customer.getCustomers_name(),customer.getCustomers_mobile(),customer.getCustomers_phone(),customer.getCustomers_email(),customer.getCustomers_drivers_license(),customer.getCustomers_drivers_license_issuedate(),customer.getCustomers_drivers_license_expiredate());
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Customer findCustomerByID(int id){
        String sqlFindCustomerById = "SELECT * FROM customers WHERE customers_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = jdbcTemplate.queryForObject(sqlFindCustomerById, rowMapper, id);
        return customer;
    }

    public List<Customer> searchForCustomer(String search){
        String sql = "SELECT customers_id, customers_name, customers_mobile, customers_phone, customers_email, customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate, address_street, address_number, address_floor, zip, city, country FROM kailua_cars.customers inner join address on address_id = customers_address inner join zipcity on address_zip = zip_id WHERE customers_name LIKE \"%" + search + "%\";";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean deleteCustomer(int id){
        return null;
    }

    /**
     *
     * @param id
     * @param customer
     * @return
     */
    public Customer updateCustomer(int id, Customer customer){
        return null;
    }

}
