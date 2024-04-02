package ru.hse.softwear.cinemaworld.restServer.util.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptUtil {

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = AESUtil.getSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptData = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptData);
    }
}
