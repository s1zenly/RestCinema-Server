package ru.hse.softwear.cinemaworld.userServer.util.BASE64;

import java.util.Base64;

public class EncryptUtil {

    public static String encrypt(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
}
