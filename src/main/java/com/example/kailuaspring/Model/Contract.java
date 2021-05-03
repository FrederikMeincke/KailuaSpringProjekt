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
    private String contracts_fromdate;
    private String contracts_todate;
    private String contracts_maxkm;
    private String contracts_startkm;
    private int contracts_cars_id;
    private int car_brand_id;
    private String car_brand;
    private int car_models_id;
    private String car_models_name;
    private int cars_id;
    private FuelType car_models_fueltype;
    private CarsType cars_type;
    private String cars_license_plate;
    private String cars_first_reg;
    private String cars_current_km;

    private Customer customer;


    public Contract(int contracts_id, int contracts_customer, String contracts_fromdate, String contracts_todate,
                    String contracts_maxkm, String contracts_startkm, int contracts_cars_id, int car_brand_id,
                    String car_brand, int car_models_id, String car_models_name, int cars_id,
                    FuelType car_models_fueltype, CarsType cars_type, String cars_license_plate, String cars_first_reg,
                    String cars_current_km) {
        this.contracts_id = contracts_id;
        this.contracts_customer = contracts_customer;
        this.contracts_fromdate = contracts_fromdate;
        this.contracts_todate = contracts_todate;
        this.contracts_maxkm = contracts_maxkm;
        this.contracts_startkm = contracts_startkm;
        this.contracts_cars_id = contracts_cars_id;
        this.car_brand_id = car_brand_id;
        this.car_brand = car_brand;
        this.car_models_id = car_models_id;
        this.car_models_name = car_models_name;
        this.cars_id = cars_id;
        this.car_models_fueltype = car_models_fueltype;
        this.cars_type = cars_type;
        this.cars_license_plate = cars_license_plate;
        this.cars_first_reg = cars_first_reg;
        this.cars_current_km = cars_current_km;
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

    public void setContracts_customer(int contracts_customer) {
        this.contracts_customer = contracts_customer;
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

    public int getContracts_cars_id() {
        return contracts_cars_id;
    }

    public void setContracts_cars_id(int contracts_cars_id) {
        this.contracts_cars_id = contracts_cars_id;
    }

    public int getCar_brand_id() {
        return car_brand_id;
    }

    public void setCar_brand_id(int car_brand_id) {
        this.car_brand_id = car_brand_id;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public int getCar_models_id() {
        return car_models_id;
    }

    public void setCar_models_id(int car_models_id) {
        this.car_models_id = car_models_id;
    }

    public String getCar_models_name() {
        return car_models_name;
    }

    public void setCar_models_name(String car_models_name) {
        this.car_models_name = car_models_name;
    }

    public int getCars_id() {
        return cars_id;
    }

    public void setCars_id(int cars_id) {
        this.cars_id = cars_id;
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

    public String getCars_license_plate() {
        return cars_license_plate;
    }

    public void setCars_license_plate(String cars_license_plate) {
        this.cars_license_plate = cars_license_plate;
    }

    public String getCars_first_reg() {
        return cars_first_reg;
    }

    public void setCars_first_reg(String cars_first_reg) {
        this.cars_first_reg = cars_first_reg;
    }

    public String getCars_current_km() {
        return cars_current_km;
    }

    public void setCars_current_km(String cars_current_km) {
        this.cars_current_km = cars_current_km;
    }
}
