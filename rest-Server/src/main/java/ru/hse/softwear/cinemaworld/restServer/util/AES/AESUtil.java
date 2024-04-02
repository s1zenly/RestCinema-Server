package ru.hse.softwear.cinemaworld.restServer.util.AES;

import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    private static final String SECRET_KEY = "gh6f7dg6g3h9k33g";

    public static SecretKeySpec getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
    }
}
