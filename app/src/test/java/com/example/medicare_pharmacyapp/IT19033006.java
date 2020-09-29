package com.example.medicare_pharmacyapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19033006 {

    int result;
    private Check_Stock check_stock;


    @Before
    public void setUp()
    {
        check_stock = new Check_Stock();
    }

    @Test
    public void TestMultiplication (){

       result = check_stock.totPrice(600,2);
       assertEquals(1200,result);

       }

    @After

    public void clearData(){
        result = 0;

    }
    }
//test