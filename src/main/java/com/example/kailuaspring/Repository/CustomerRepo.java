package com.example.kailuaspring.Repository;

import com.example.kailuaspring.Model.Car;
import com.example.kailuaspring.Model.Contract;
import com.example.kailuaspring.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.List;

@Repository

public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Customers
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

    public Customer findCustomerByID(int id){
        String sqlFindCustomerById = "SELECT customers_id, customers_name, customers_mobile, customers_phone," +
                " customers_email, customers_drivers_license, customers_drivers_license_issuedate," +
                " customers_drivers_license_expiredate, address_street, address_number, address_floor, zip, city," +
                " country, zip_id, address_id" +
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

    public void addCustomer(Customer customer){
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

        jdbcTemplate.update(sqlCustomer,customer.getCustomers_name(),customer.getCustomers_mobile(),
                customer.getCustomers_phone(),customer.getCustomers_email(),customer.getCustomers_drivers_license(),
                customer.getCustomers_drivers_license_issuedate(),customer.getCustomers_drivers_license_expiredate());
    }

    public void updateCustomer(int id, Customer tCustomer){
        Customer customer = findCustomerByID(id);
        int zipId = customer.getZip_id();
        int addressId = customer.getAddress_id();

        try {
            String sqlzip = "UPDATE zipcity " +
                    "SET zip = ?, city = ? " +
                    "WHERE zip_id  = ?; ";
            jdbcTemplate.update(sqlzip,tCustomer.getZip(), tCustomer.getCity(),zipId);

            String addresssql = "UPDATE address " +
                    "SET address_street = ?, address_number = ?, address_floor = ? " +
                    "WHERE address_id = ?;";
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

    public boolean deleteCustomer(int id){
        try {
            String sql = "DELETE FROM customers WHERE customers_id = ?";
            return jdbcTemplate.update(sql, id) < 0;
        } catch (DataIntegrityViolationException e) {
            return true;
        }

    }

    //Contracts

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

    public Contract findContractByID(int id) {
        String sql = "SELECT contracts_id, cu.customers_name, contracts_fromdate, contracts_todate, contracts_maxkm," +
                " contracts_startkm, c.cars_license_plate, cb.car_brand, cm.car_models_name, cm.car_models_fueltype," +
                " cm.cars_type FROM" +
                " contracts " +
                "JOIN customers cu on contracts.contracts_customer = cu.customers_id " +
                "JOIN cars c on c.cars_id = contracts.contracts_cars_id " +
                "JOIN car_models cm on c.cars_model_id = cm.car_models_id " +
                "JOIN car_brands cb on cb.car_brand_id = cm.car_models_brand_id " +
                "WHERE contracts_id = ?;";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        Contract contract = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return contract;
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

    public void addContract(Contract contract) { //todo
    }

    public void updateContract() { //todo

    }

    public boolean deleteContract() { //todo
        return false;
    }

    //Cars

    public List<Car> fetchAllCars() {
        String sql = "SELECT cars_id, car_brand, car_models_name, car_models_fueltype, cars_type, cars_license_plate, " +
                "cars_first_reg, cars_current_km, cars_model_id, car_models_brand_id FROM cars " +
                "join car_models cm on cars.cars_model_id = cm.car_models_id " +
                "join car_brands cb on cm.car_models_brand_id = cb.car_brand_id;";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Car findCarByID(int id) {
        String sql = "SELECT cars_id, car_brand, car_models_name, car_models_fueltype, cars_type, cars_license_plate, " +
                "cars_first_reg, cars_current_km, cars_model_id, car_models_brand_id FROM cars " +
                "join car_models cm on cars.cars_model_id = cm.car_models_id " +
                "join car_brands cb on cm.car_models_brand_id = cb.car_brand_id " +
                "WHERE cars_id = ?;";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car car = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return car;

    }

    public void addCar(Car car) { //todo

        try {
            String brandsql = "INSERT INTO car_brands " +
                    "VALUES (?);";
            jdbcTemplate.update(brandsql, car.getCar_brand());
            String lastAddedBrand = "SELECT * FROM car_brands " +
                    "ORDER BY car_brand_id DESC LIMIT 1;";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(lastAddedBrand);
            rowSet.next();
            int lastBrandId = rowSet.getInt(1);

            String modelsql = "INSERT INTO car_models (car_models_brand_id, car_models_name, car_models_fueltype, cars_type) " +
                    "VALUES (?, ?, ?, ?);";
            jdbcTemplate.update(modelsql, lastBrandId, car.getCar_models_name(), car.getCar_models_fueltype(), car.getCars_type());

            String lastAddedModel = "SELECT * FROM car_models " +
                    "ORDER BY car_models_id DESC LIMIT 1;";
            rowSet = jdbcTemplate.queryForRowSet(lastAddedBrand);
            rowSet.next();
            int lastModelId = rowSet.getInt(1);
            String carsql = "INSERT INTO cars (cars_model_id, cars_license_plate, cars_first_reg, cars_current_km) " +
                    "VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(carsql, lastModelId, car.getCars_license_plate(),
                    car.getCars_first_reg(), car.getCars_current_km());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
    }

    public void updateCar(int id, Car tCar) {
        Car car = findCarByID(id);
        int modelId = car.getCars_model_id();
        int brandId = car.getCar_models_brand_id();

        try {
            String brandsql = "UPDATE car_brands " +
                    "SET car_brand = ? " +
                    "WHERE car_brand_id = ?;";
            jdbcTemplate.update(brandsql, tCar.getCar_brand(), brandId);

            String modelsql = "UPDATE car_models " +
                    "SET car_models_name = ?, car_models_fueltype = ?, cars_type = ?" +
                    "WHERE car_models_id = ?;";
            jdbcTemplate.update(modelsql, tCar.getCar_models_name(), tCar.getCar_models_fueltype(),
                    tCar.getCars_type(), modelId);

            String carsql = "UPDATE cars " +
                    "SET cars_license_plate = ?, cars_first_reg = ?, cars_current_km = ? " +
                    "WHERE cars_id = ?;";
            jdbcTemplate.update(carsql, tCar.getCars_license_plate(), tCar.getCars_first_reg(),
                    tCar.getCars_current_km(), car.getCars_id());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
    }

    public boolean deleteCar(int id) {
        try {
            String sql = "DELETE FROM cars WHERE  cars_id = ?";
            return jdbcTemplate.update(sql, id) < 0;
        } catch (DataIntegrityViolationException e) {
            return true;
        }
    }


    }
