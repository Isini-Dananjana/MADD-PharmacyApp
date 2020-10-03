package com.example.medicare_pharmacyapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class IT19014814 {

    static private My_Cart my_cart;
private int oneTypeProductTPrice;

    @BeforeClass
    public static void setUp() throws Exception {
        my_cart = new My_Cart();
    }
    @Before
    public void Price_Quantity(){
        oneTypeProductTPrice = 0;
    }
    @Test
    public void oneProductPrice_isCorrect() {

        int oneTypeProductTPrice = (int)my_cart.oneProductPrice(400,3);
        assertEquals(1200, oneTypeProductTPrice, 0.001);
    }
    @Test
    public void oneProductPrice_isCorrect1() {

        int oneTypeProductTPrice = (int)my_cart.oneProductPrice(1200,2);
        assertEquals(2400, oneTypeProductTPrice, 0.01);
    }
    @Test
    public void oneProductPrice_isinCorrect(){
        int oneTypeProductTPrice = (int)my_cart.oneProductPrice(300,3);
        assertNotEquals(1200, oneTypeProductTPrice, 0.001);
    }
    @Test
    public void oneProductPrice_isinCorrect1(){
        int oneTypeProductTPrice = (int)my_cart.oneProductPrice(200,3);
        assertNotEquals(3000, oneTypeProductTPrice, 0.001);
    }
    @After
    public void tearDown() throws Exception {

        oneTypeProductTPrice = 0;
    }
    @AfterClass
    public static void deleteOb(){
        my_cart = null;
    }
}