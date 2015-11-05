package com.Test;

import android.test.InstrumentationTestCase;

/**
 * Created by Clark on 11/4/15.
 */
public class TestClass extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}
