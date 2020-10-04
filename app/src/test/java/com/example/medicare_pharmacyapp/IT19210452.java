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

    static private Confirm_Order confirm_order;
    static private Delivery delivery;

    int tot;
    boolean PhoneNo;
    boolean Cname;

    @BeforeClass
    public static void initMain() throws Exception {
        confirm_order = new Confirm_Order();
        delivery = new Delivery();
    }

    @Before
    public void setUp(){
        tot=0;
        PhoneNo= false;
        Cname= false;
    }

    @Test
    public void finalTot1Cal_isCorrect() {


         tot = confirm_order.finalTotCal(100,1220);
        assertEquals(1320, tot, 0.001);
    }

    @Test
    public void finalTot2Cal_isCorrect() {


        tot = confirm_order.finalTotCal(100,800);
        assertEquals(900, tot, 0.001);
    }
    @Test
    public void finalTot3Cal_isCorrect() {


        tot = confirm_order.finalTotCal(100,700);
        assertNotEquals(1000, tot, 0.001);
    }

    @Test
    public void PhoneNo1_correct()
    {
        PhoneNo = delivery.isValidPhoneNum("0774056789");
        assertTrue(PhoneNo);
    }
    @Test
    public void PhoneNo2_correct()
    {
        PhoneNo = delivery.isValidPhoneNum("0776paa567");
        assertFalse(PhoneNo);
    }
    @Test
    public void PhoneNo3_correct()
    {
        PhoneNo = delivery.isValidPhoneNum("");
        assertFalse(PhoneNo);
    }
    @Test
    public  void  PhoneNo4_correct()
    {
        PhoneNo = delivery.isValidPhoneNum("077");
        assertFalse(PhoneNo);
    }
    @Test
    public void PhoneNo5_correct()
    {
        PhoneNo = delivery.isValidPhoneNum("abcd");
        assertFalse(PhoneNo);
    }
    @Test
    public void Cname2_iscorrect()
    {
        Cname = delivery.isValidName("Sandali");
        assertTrue(Cname);
    }
    @Test
    public  void  Cname1_iscorrect()
    {
        Cname = delivery.isValidName("San11");
        assertFalse(Cname);
    }
    @After
    public void clearData()throws Exception
    {
        tot = 0;
        PhoneNo = false;
        Cname = false;
    }
    @AfterClass
    public static void deleteOb(){
        delivery = null;
        confirm_order= null;
    }
}