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

    @BeforeClass
    public static void createObj(){

        registerActivity = new RegisterActivity();
    }
    @Before
    public  void set(){


        phone = false;
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


        phone=registerActivity.isValidatePhone("eeeeee");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect4() {


        phone=registerActivity.isValidatePhone("");
        assertFalse(phone);
    }
    @Test
    public void phone_isCorrect5() {


        phone=registerActivity.isValidatePhone("345gggf");
        assertFalse(phone);
    }
    @After
    public void clear(){
        phone=false;
    }
    @BeforeClass
    public static void clearAll(){
        registerActivity=null;
    }
}
