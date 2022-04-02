package com.example.runqr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests Hasher and QRCode classes
 */
public class QRCodeHasherTest {

    Hasher testHasher = new Hasher();
    String testString = "hello";
    String hashedTestString = testHasher.hashQRCode(testString);
    QRCode testCode = new QRCode(hashedTestString);

    @Test
    public void testCharCount(){
        HashMap<Character, Integer> charCount = testCode.characterCount(hashedTestString);

        for (Map.Entry entry : charCount.entrySet()) {

            if(entry.getKey() == "h") {
                assertEquals(entry.getValue(), 1);
            }
            else if(entry.getKey() == "e") {
                assertEquals(entry.getValue(), 1);
            }
            else if(entry.getKey() == "l") {
                assertEquals(entry.getValue(), 2);
            }
            else if(entry.getKey() == "o") {
                assertEquals(entry.getValue(), 1);
            }
        }
    }

    @Test
    public void testScoreQRCode(){
        int expectedScore = 13; //calculated based on algorithm
        //Show calculation here:

        assertEquals(testCode.getScore(), expectedScore);
    }



}
