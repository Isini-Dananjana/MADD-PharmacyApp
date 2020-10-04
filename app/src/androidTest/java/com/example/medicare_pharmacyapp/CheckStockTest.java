package com.example.medicare_pharmacyapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;


@RunWith(AndroidJUnit4.class)
public class CheckStockTest {

    private String name = "Bio oil"; private String qty = "5";
    private String price = "450";


    @Rule
    public ActivityScenarioRule<Check_Stock> activityRule
            = new ActivityScenarioRule<>(Check_Stock.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInput()

    {
       Espresso.onView(withId(R.id.product_name)).check(matches(withText(name)));
        Espresso.onView(withId(R.id.product_price)).check(matches(withText(price)));
        Espresso.onView(withId(R.id.product_quantity)).check(matches(withText(qty)));



    }


    @After
    public void tearDown() throws Exception {
    }
}