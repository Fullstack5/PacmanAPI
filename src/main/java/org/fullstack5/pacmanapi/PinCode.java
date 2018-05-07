package org.fullstack5.pacmanapi;

import java.util.Random;

public class PinCode {

    static String create() {
        Random random = new Random();

        char[] pinCode = new char[4];
        for (int i = 0; i < pinCode.length; i++) {
            pinCode[i] = (char)('a' + random.nextInt(26));
        }
        return new String(pinCode);
    }
}
