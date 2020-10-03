package com.example.medicare_pharmacyapp.IT19033006;

import com.example.medicare_pharmacyapp.AddItem;
import com.example.medicare_pharmacyapp.Check_Stock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19033006_2 {

    private static AddItem addItem;
    boolean price ;



    @BeforeClass
    public static void initCheck_stock()
    {
        addItem = new AddItem();
    }
    @Before
    public void setUp()
    {
        price = false;

    }

    @Test
    public void isCorretPrice1 (){

       price = addItem.isValidatePrice("340");
        assertTrue(price);


       }

    @Test
    public void isCorretPrice2 (){

        price = addItem.isValidatePrice("340LKR");
        assertFalse(price);

    }

    @Test
    public void isCorretPrice3(){

        price = addItem.isValidatePrice("LKR");
        assertFalse(price);

    }



    @After

    public void clearData(){
        price = false;

    }

    @AfterClass
    public  static void clearAll()
    {
        addItem = null;

    }
}
//test
//It19033006