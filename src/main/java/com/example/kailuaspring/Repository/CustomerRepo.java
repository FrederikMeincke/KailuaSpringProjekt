package com.example.kailuaspring.Repository;

import com.example.kailuaspring.Model.Contract;
import com.example.kailuaspring.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * @return
     */
    public List<Customer> fetchAllCustomers(){
        String sql = "SELECT customers_id, customers_name, customers_mobile, customers_phone, customers_email," +
                " customers_drivers_license, customers_drivers_license_issuedate, customers_drivers_license_expiredate," +
                " address_street, address_number, address_floor, zip, city, country, zip_id, address_id" +
                " FROM kailua_cars.customers " +
                "inner join address on address_id = customers_address " +
                "inner join zipcity on address_zip = zip_id;";

        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    public List<Contract> fetchAllContracts() {
        String sql = "SELECT contracts_id, cu.customers_name, contracts_fromdate, contracts_todate, contracts_maxkm," +
                " contracts_startkm, c.cars_license_plate, cb.car_brand, cm.car_models_name, cm.car_models_fueltype," +
                " cm.cars_type FROM" +
                " contracts " +
                "JOIN customers cu on contracts.contracts_customer = cu.customers_id " +
                "JOIN cars c on c.cars_id = contracts.contracts_cars_id " +
                "JOIN car_models cm on c.cars_model_id = cm.car_models_id " +
                "JOIN car_brands cb on cb.car_brand_id = cm.car_models_brand_id;";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    public List<Contract> searchForContractsByCustomer(int id) {
        String sql = "SELECT contracts_id, cu.customers_name, contracts_fromdate, contracts_todate, contracts_maxkm," +
                " contracts_startkm, c.cars_license_plate, cb.car_brand, cm.car_models_name, cm.car_models_fueltype," +
                " cm.cars_type FROM" +
                " contracts " +
                "JOIN customers cu on contracts.contracts_customer = cu.customers_id " +
                "JOIN cars c on c.cars_id = contracts.contracts_cars_id " +
                "JOIN car_models cm on c.cars_model_id = cm.car_models_id " +
                "JOIN car_brands cb on cb.car_brand_id = cm.car_models_brand_id " +
                "WHERE cu.customers_id = ?;";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return jdbcTemplate.query(sql,rowMapper, id);

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

        jdbcTemplate.update(sqlCustomer,customer.getCustomers_name(),customer.getCustomers_mobile(),customer.getCustomers_phone(),customer.getCustomers_email(),customer.getCustomers_drivers_license(),customer.getCustomers_drivers_license_issuedate(),customer.getCustomers_drivers_license_expiredate());
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Customer findCustomerByID(int id){
        String sqlFindCustomerById = "SELECT customers_id, customers_name, customers_mobile, customers_phone," +
                " customers_email, customers_drivers_license, customers_drivers_license_issuedate," +
                " customers_drivers_license_expiredate, address_street, address_number, address_floor, zip, city," +
                " country" +
                " FROM kailua_cars.customers" +
                " inner join address on address_id = customers_address" +
                " inner join zipcity on address_zip = zip_id" +
                " WHERE customers_id = ?;";
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
        try {
            String sql = "DELETE FROM customers WHERE customers_id = ?";
            return jdbcTemplate.update(sql, id) < 0;
        } catch (Exception e) {
            return true;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public void updateCustomer(int id, Customer tCustomer){
        Customer customer = findCustomerByID(id);
        int zipId = customer.getZipId();
        int addressId = customer.getAddressId();
        try {
            String sqlzip = "UPDATE zipcity " +
            "SET zip = ?, city = ?, country = ? " +
            "WHERE zip_id  in " +
                    "(SELECT * FROM (SELECT zip_id " +
                            "FROM zipcity " +
                            "JOIN address a on zipcity.zip_id = a.address_zip " +
                            "WHERE address_id = ?)tbltemp); ";
            jdbcTemplate.update(sqlzip,tCustomer.getZip(), tCustomer.getCity(), tCustomer.getCountry(),zipId);

            String addresssql = "UPDATE address " +
                    "SET address_street = ?, address_number = ?, address_floor = ? " +
                    "WHERE address_id in " +
                    "      (SELECT * FROM(SELECT address_id " +
                    "                     FROM address " +
                    "                              JOIN customers c on address_id = c.customers_address " +
                    "                     WHERE c.customers_id = ?)tbltemp);";
            jdbcTemplate.update(addresssql,tCustomer.getAddress_street(),tCustomer.getAddress_number(),
                    tCustomer.getAddress_floor(), addressId);

            String customersql ="UPDATE customers as cu " +
                    "SET cu.customers_name = ?, cu.customers_mobile = ?, cu.customers_phone = ?, cu.customers_email = ?, " +
                    "    cu.customers_drivers_license = ?, " +
                    "    cu.customers_drivers_license_issuedate = ?, cu.customers_drivers_license_expiredate = ? " +
                    "WHERE cu.customers_id = ?;";
            jdbcTemplate.update(customersql,tCustomer.getCustomers_name(), tCustomer.getCustomers_mobile(),
                    tCustomer.getCustomers_phone(),tCustomer.getCustomers_email(), tCustomer.getCustomers_drivers_license(),
                    tCustomer.getCustomers_drivers_license_issuedate(), tCustomer.getCustomers_drivers_license_expiredate(),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

}
