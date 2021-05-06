package com.example.kailuaspring.Model;


public class Contract {
    private enum FuelType{
        EV,
        Petrol,
        Diesel
    }

    private enum CarsType {
        LUX,
        FAM,
        ECO,
        SPRT
    }

    private int contracts_id;
    private int contracts_customer;
    private String customers_name;
    private String contracts_fromdate;
    private String contracts_todate;
    private String contracts_maxkm;
    private String contracts_startkm;
    private String cars_license_plate;
    private int contracts_cars_id;
    private String car_brand;
    private String car_models_name;
    private FuelType car_models_fueltype;
    private CarsType cars_type;

    public Contract(int contracts_id, int contracts_customer, String customers_name, String contracts_fromdate,
                    String contracts_todate, String contracts_maxkm, String contracts_startkm,
                    String cars_license_plate, int contracts_cars_id, String car_brand, String car_models_name,
                    FuelType car_models_fueltype, CarsType cars_type) {
        this.contracts_id = contracts_id;
        this.contracts_customer = contracts_customer;
        this.customers_name = customers_name;
        this.contracts_fromdate = contracts_fromdate;
        this.contracts_todate = contracts_todate;
        this.contracts_maxkm = contracts_maxkm;
        this.contracts_startkm = contracts_startkm;
        this.cars_license_plate = cars_license_plate;
        this.contracts_cars_id = contracts_cars_id;
        this.car_brand = car_brand;
        this.car_models_name = car_models_name;
        this.car_models_fueltype = car_models_fueltype;
        this.cars_type = cars_type;
    }

    public Contract() {

    }

    public int getContracts_id() {
        return contracts_id;
    }

    public void setContracts_id(int contracts_id) {
        this.contracts_id = contracts_id;
    }

    public int getContracts_customer() {
        return contracts_customer;
    }

    public void setContracts_customer(int contract_customer) {
        this.contracts_customer = contract_customer;
    }

    public String getCustomers_name() {
        return customers_name;
    }

    public void setCustomers_name(String customers_name) {
        this.customers_name = customers_name;
    }

    public String getContracts_fromdate() {
        return contracts_fromdate;
    }

    public void setContracts_fromdate(String contracts_fromdate) {
        this.contracts_fromdate = contracts_fromdate;
    }

    public String getContracts_todate() {
        return contracts_todate;
    }

    public void setContracts_todate(String contracts_todate) {
        this.contracts_todate = contracts_todate;
    }

    public String getContracts_maxkm() {
        return contracts_maxkm;
    }

    public void setContracts_maxkm(String contracts_maxkm) {
        this.contracts_maxkm = contracts_maxkm;
    }

    public String getContracts_startkm() {
        return contracts_startkm;
    }

    public void setContracts_startkm(String contracts_startkm) {
        this.contracts_startkm = contracts_startkm;
    }

    public String getCars_license_plate() {
        return cars_license_plate;
    }

    public void setCars_license_plate(String cars_license_plate) {
        this.cars_license_plate = cars_license_plate;
    }

    public int getContracts_cars_id() {
        return contracts_cars_id;
    }

    public void setContracts_cars_id(int contracts_cars_id) {
        this.contracts_cars_id = contracts_cars_id;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_models_name() {
        return car_models_name;
    }

    public void setCar_models_name(String car_models_name) {
        this.car_models_name = car_models_name;
    }

    public FuelType getCar_models_fueltype() {
        return car_models_fueltype;
    }

    public void setCar_models_fueltype(FuelType car_models_fueltype) {
        this.car_models_fueltype = car_models_fueltype;
    }

    public CarsType getCars_type() {
        return cars_type;
    }

    public void setCars_type(CarsType cars_type) {
        this.cars_type = cars_type;
    }
}
