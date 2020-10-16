package com.example.medicare_pharmacyapp;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class IT19150444 {
    private static RegisterActivity registerActivity;
    //  boolean phone =registerActivity.findViewById(R.id.login_phone_num_Input);
    boolean phone;
    boolean password;

    @BeforeClass
    public static void createObj(){
        registerActivity = new RegisterActivity();
    }
    @Before
    public  void set(){
        phone = false;
        password = false;
    }
    @Test
    public void phone_isCorrect1() {
        phone=registerActivity.isValidatePhone("0711111111");
        assertTrue(phone);
    }
    @Test
    public void phone_isCorrect2() {
        phone=registerActivity.isValidatePhone("07111");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect3() {
        phone=registerActivity.isValidatePhone("07111111111111111111");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect4() {
        phone=registerActivity.isValidatePhone("eeeeee");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect5() {
        phone=registerActivity.isValidatePhone("");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect6() {
        phone=registerActivity.isValidatePhone("345gggf");
        assertFalse(phone);
    }
    @Test
    public void password_isCorrect1() {
        password=registerActivity.isValidatePassword("123");
        assertFalse(password);
    }
    @Test
    public void password_isCorrect2() {
        password=registerActivity.isValidatePassword("ayesha123");
        assertTrue(password);
    }
    @Test
    public void password_isCorrect3() {
        password=registerActivity.isValidatePassword("");
        assertFalse(password);
    }
    @Test
    public void password_isCorrect4() {
        password=registerActivity.isValidatePassword("ashy");
        assertTrue(password);
    }
    @After
    public void clear(){
        phone=false;
        password=false;
    }
    @BeforeClass
    public static void clearAll(){
        registerActivity=null;
    }
}
