package ru.hse.softwear.cinemaworld.restServer.util.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DecryptUtil {

    public static String decrypt(String data) throws Exception {
        SecretKeySpec key = AESUtil.getSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptData = cipher.doFinal(Base64.getDecoder().decode(data));

        return new String(decryptData);
    }
}
