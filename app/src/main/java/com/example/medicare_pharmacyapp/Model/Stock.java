package com.example.medicare_pharmacyapp.Model;

public class Stock {
    public Stock() {
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public int getTot() {
        return tot;
    }

    public void setTot(int tot) {
        this.tot = tot;
    }


    public Stock(String pname, String price, String category, String pid, String quantity) {
        this.pname = pname;
        this.price = price;
        this.category = category;
        this.pid = pid;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
        this.productState = productState;
        this.tot = tot;
    }

    private String pname, price, category, pid, quantity, date, time, productState;
    int tot;

}
