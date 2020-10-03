package com.example.medicare_pharmacyapp.IT19033006;

import com.example.medicare_pharmacyapp.AddItem;
import com.example.medicare_pharmacyapp.AdminStock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19033006_3 {

    private static AdminStock adminStock;
    boolean price , qty ;



    @BeforeClass
    public static void initCheck_stock()
    {
        adminStock = new AdminStock();
    }
    @Before
    public void setUp()
    {
        price = false;
        qty = false;

    }

    @Test
    public void isCorrectprice (){

       price = adminStock.isValidatePrice("340");
        assertTrue(price);


       }

    @Test
    public void isCorrectprice2 (){

        price = adminStock.isValidatePrice("340LKR");
        assertFalse(price);

    }

    @Test
    public void isCorrectprice3(){

        price = adminStock.isValidatePrice("LKR");
        assertFalse(price);

    }


    @Test
    public void isCorrectqty3 (){

        price = adminStock.isValidateQty("40");
        assertTrue(price);


    }

    @Test
    public void isCorrectqty1 (){

        price = adminStock.isValidateQty("40LKR");
        assertFalse(price);

    }

    @Test
    public void isCorrectqty2(){

        price = adminStock.isValidateQty("LKR");
        assertFalse(price);

    }



    @After

    public void clearData(){
        price = false;
        qty = false;

    }

    @AfterClass
    public  static void clearAll()
    {
        adminStock = null;

    }
}
//test
//It19033006