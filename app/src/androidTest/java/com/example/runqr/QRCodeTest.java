package com.example.runqr;

import org.junit.Before;

public class QRCodeTest {

    @Before
    public void setUpQRCode(){
        String testHash = "hellomyNameisHelen";
        QRCode testCode = new QRCode(testHash);
    }

}
