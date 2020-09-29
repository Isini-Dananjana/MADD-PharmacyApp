package com.example.medicare_pharmacyapp;

import  com.example.medicare_pharmacyapp.Confirm_Order;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
/*import com.google.common.truth.Truth.assertThat;*/

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19210452 {

    private Confirm_Order confirm_order;
    int tot;

 //   @BeforeClass

    @Before
    public void createObj(){

        confirm_order = new Confirm_Order();
    }

    @Test
    public void finalTotCal_isCorrect() {


         tot = confirm_order.finalTotCal(100,1220);
        assertEquals(1320, tot, 0.001);
    }
    @After
    public void clearData()
    {
        tot = 0;
    }

  //@AfterClass
}