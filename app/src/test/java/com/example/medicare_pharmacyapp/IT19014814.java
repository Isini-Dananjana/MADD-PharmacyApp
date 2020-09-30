package com.example.medicare_pharmacyapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IT19014814 {

    private My_Cart my_cart;

    @Before
    public void setUp() throws Exception {
        my_cart = new My_Cart();
    }

    @Test
    public void oneProductPrice() {

        int oneTypeProductTPrice = my_cart.oneProductPrice(300,3);
        assertEquals(900, oneTypeProductTPrice, 0.001);
    }

    @After
    public void tearDown() throws Exception {

        int oneTypeProductTPrice = 0;
    }


}