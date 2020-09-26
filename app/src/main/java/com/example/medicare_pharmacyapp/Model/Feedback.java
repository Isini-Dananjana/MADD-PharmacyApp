package com.example.medicare_pharmacyapp.Model;

import com.hsalf.smilerating.SmileRating;

public class Feedback {
   // private SmileRating smileRating;
    private String phone,feedback,date,time,smileRating;

    public Feedback() {

   }

    public Feedback(String phone, String feedback, String date, String time, String smileRating) {
        this.phone = phone;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
        this.smileRating = smileRating;
    }

    public String getSmileRating() {
        return smileRating;
    }

    public void setSmileRating(String smileRating) {
        this.smileRating = smileRating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate() {
        return this.date;
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
}
