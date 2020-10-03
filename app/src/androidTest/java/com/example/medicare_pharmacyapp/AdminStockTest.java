package com.example.medicare_pharmacyapp;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AdminStockTest {


    private String name = "Bio oil"; private String qty = "5";
    private String price = "450";

    @Rule
    public ActivityScenarioRule<AdminStock> activityRule
            = new ActivityScenarioRule<>(AdminStock.class);



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInput()

    {
        Espresso.onView(withId(R.id.product_name)).perform(typeText(name));
        Espresso.onView(withId(R.id.product_price)).perform(typeText(price));
        Espresso.onView(withId(R.id.product_quantity)).perform(typeText(qty));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.add_new_stock)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }
}