package org.example;


import org.junit.*;

import java.lang.annotation.Inherited;

import static org.junit.Assert.*;

public class JUnit {

    // execute before class
    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    // execute after class
    @AfterClass
    public static void afterClass() {
        System.out.println("in after class");
    }

    // execute before test
    @Before
    public void before() {
        System.out.println("in before");
    }

    // execute after test
    @After
    public void after() {
        System.out.println("in after");
    }

    // test
    @Test
    public void test() {
        System.out.println("in test");
    }

    @Test
    public void testAssertions() {
        // test data
        String str1 = new String("abc");
        String str2 = new String ("abc");
        String str3 = null;
        String str4 = "abc";
        String str5 = "abc";

        int val1 = 5;
        int val2 = 6;

        String[] expectedArray = {"one", "two", "three"};
        String[] resultArray = {"one", "two", "three"};

        // Check that two objects are equal
        assertEquals(str1, str2);

        // Check that a condition is true
        assertTrue(val1 < val2);

        // Check that a condition is false
        assertFalse(val1 > val2);

        // Check that an object isn't null
        assertNotNull(str1);

        // Check that an object is null
        assertNull(str3);

        // Check if two object references point to the same object
        assertSame(str4, str5);

        // Check if two object references not point to the same object
        assertNotSame(str1, str3);

        // Check whether two arrays are equal to each other
        assertArrayEquals(expectedArray, resultArray);
    }

    // test case ignore and will not execute
    @Ignore
    public void ignoreTest() {
        System.out.println("in ignore test");
    }


}
