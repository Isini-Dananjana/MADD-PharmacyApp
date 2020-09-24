package com.example.medicare_pharmacyapp.Model;

public class DeliveryModel {

    private String name, phone, Address, City, date,  DeliveryId, time,totalAmount,state,deliveryTot,finalTot;


    public DeliveryModel(){

    }




    public DeliveryModel(String name, String phone, String address, String city, String date, String deliveryId, String time, String totalAmount, String state, String deliveryTot, String finalTot) {
        this.name = name;
        this.phone = phone;
        Address = address;
        City = city;
        this.date = date;
        DeliveryId = deliveryId;
        this.time = time;
        this.totalAmount = totalAmount;
        this.state = state;
        this.deliveryTot = deliveryTot;
        this.finalTot = finalTot;



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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeliveryTot() {
        return deliveryTot;
    }

    public void setDeliveryTot(String deliveryTot) {
        this.deliveryTot = deliveryTot;
    }

    public String getFinalTot() {
        return finalTot;
    }

    public void setFinalTot(String finalTot) {
        this.finalTot = finalTot;
    }
}
