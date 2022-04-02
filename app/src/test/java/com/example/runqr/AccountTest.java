package com.example.runqr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests Account class
 */
public class AccountTest {

    String testUsername = "test_username";
    String testEmail = "test_email";
    Account testAccount = new Account(testUsername, testEmail);

    @Test
    public void testDetails(){
        assertEquals(testAccount.getUsername(), testUsername);
        assertEquals(testAccount.getContactEmail(), testEmail);
    }
}
