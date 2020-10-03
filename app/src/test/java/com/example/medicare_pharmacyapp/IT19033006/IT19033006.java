package com.example.medicare_pharmacyapp.IT19033006;

import com.example.medicare_pharmacyapp.Check_Stock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19033006 {

    private static Check_Stock check_stock;
    int result;



    @BeforeClass
    public static void initCheck_stock()
    {
        check_stock = new Check_Stock();
    }
    @Before
    public void setUp()
    {
        result = 0;

    }

    @Test
    public void TestMultiplication (){

       result = check_stock.totPrice(600,2);
       assertEquals(1200,result);


       }

    @Test
    public void TestMultiplication2 (){

        result = check_stock.totPrice(800,0);
        assertEquals(0,result);
    }

    @After

    public void clearData(){
        result = 0;

    }

    @AfterClass
    public  static void clearAll()
    {
        check_stock = null;

    }
}
//test
//It19033006