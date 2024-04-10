package ru.hse.softwear.cinemaworld.userServer.util.BASE64;

import java.util.Base64;

public class DecryptUtil {

    public static byte[] decrypt(String data) throws Exception {
        return Base64.getUrlDecoder().decode(data);
    }
}
