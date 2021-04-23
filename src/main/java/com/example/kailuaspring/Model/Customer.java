package com.example.kailuaspring.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private int customers_id;
    private String customers_name;
    private int customers_mobile;
    private int customers_phone;
    private String customers_email;
    private String customers_drivers_license;
    private String customers_drivers_license_issuedate;
    private String customers_drivers_license_expiredate;
    private String address_street;
    private String address_number;
    private String address_floor;
    private int zip;
    private String city;
    private String country;

    public Customer() {
    }

    public Customer(int customers_id, String customers_name, int customers_mobile, int customers_phone,
                    String customers_email, String customers_drivers_license, String customers_drivers_license_issuedate,
                    String customers_drivers_license_expiredate, String address_street, String address_number,
                    String address_floor, int zip, String city, String country) {
        this.customers_id = customers_id;
        this.customers_name = customers_name;
        this.customers_mobile = customers_mobile;
        this.customers_phone = customers_phone;
        this.customers_email = customers_email;
        this.customers_drivers_license = customers_drivers_license;
        this.customers_drivers_license_issuedate = customers_drivers_license_issuedate;
        this.customers_drivers_license_expiredate = customers_drivers_license_expiredate;
        this.address_street = address_street;
        this.address_number = address_number;
        this.address_floor = address_floor;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }


    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    public String getCustomers_name() {
        return customers_name;
    }

    public void setCustomers_name(String customers_name) {
        this.customers_name = customers_name;
    }

    public int getCustomers_mobile() {
        return customers_mobile;
    }

    public void setCustomers_mobile(int customers_mobile) {
        this.customers_mobile = customers_mobile;
    }

    public int getCustomers_phone() {
        return customers_phone;
    }

    public void setCustomers_phone(int customers_phone) {
        this.customers_phone = customers_phone;
    }

    public String getCustomers_email() {
        return customers_email;
    }

    public void setCustomers_email(String customers_email) {
        this.customers_email = customers_email;
    }

    public String getCustomers_drivers_license() {
        return customers_drivers_license;
    }

    public void setCustomers_drivers_license(String customers_drivers_license) {
        this.customers_drivers_license = customers_drivers_license;
    }

    public String getCustomers_drivers_license_issuedate() {
        return customers_drivers_license_issuedate;
    }

    public void setCustomers_drivers_license_issuedate(String customers_drivers_license_issuedate) {
        this.customers_drivers_license_issuedate = customers_drivers_license_issuedate;
    }

    public String getCustomers_drivers_license_expiredate() {
        return customers_drivers_license_expiredate;
    }

    public void setCustomers_drivers_license_expiredate(String customers_drivers_license_expiredate) {
        this.customers_drivers_license_expiredate = customers_drivers_license_expiredate;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_number() {
        return address_number;
    }

    public void setAddress_number(String address_number) {
        this.address_number = address_number;
    }

    public String getAddress_floor() {
        return address_floor;
    }

    public void setAddress_floor(String address_floor) {
        this.address_floor = address_floor;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
