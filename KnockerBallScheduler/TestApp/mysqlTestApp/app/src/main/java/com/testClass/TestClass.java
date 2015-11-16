package com.testClass;

import android.test.InstrumentationTestCase;

/**
 * Created by rhuarc on 11/4/15.
 */
public class TestClass extends InstrumentationTestCase {
    public void test() throws Exception {
        int expected = 1;
        int actual = 13;
        assertEquals(expected, actual);
    }
}
