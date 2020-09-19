package com.example.medicare_pharmacyapp.Model;

public class Delivery {

    private String CustomerName, PhoneNo, Address, City, date,  DeliveryId, time;


    public Delivery(){

    }


    public Delivery(String customerName, String phoneNo, String address, String city, String date, String deliveryId, String time) {
        CustomerName = customerName;
        PhoneNo = phoneNo;
        Address = address;
        City = city;
        this.date = date;
        DeliveryId = deliveryId;
        this.time = time;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryId() {
        return DeliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        DeliveryId = deliveryId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
