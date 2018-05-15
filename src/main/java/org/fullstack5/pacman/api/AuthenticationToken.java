package org.fullstack5.pacman.api;

import java.security.SecureRandom;

public class AuthenticationToken {

    public static String create() {
        SecureRandom random = new SecureRandom();

        char[] authenticationToken = new char[64];
        for (int i = 0; i < authenticationToken.length; i++) {
            authenticationToken[i] = (char)('a' + random.nextInt(26));
        }
        return new String(authenticationToken);
    }
}
