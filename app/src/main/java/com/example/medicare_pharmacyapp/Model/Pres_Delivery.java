package com.example.medicare_pharmacyapp.Model;

public class Pres_Delivery {

    private String name, phone, Address, City, date,  DeliveryId, time,State ,image;



    public Pres_Delivery(){

    }

    public Pres_Delivery(String name, String phone, String address, String city, String date, String deliveryId, String time, String State, String image) {
        this.name = name;
        this.phone = phone;
        Address = address;
        City = city;
        this.date = date;
        DeliveryId = deliveryId;
        this.time = time;
        this.State = State;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
