package ru.hse.softwear.cinemaworld.userServer.cypher;

import org.mindrot.jbcrypt.BCrypt;

public class PersonPasswordCypher {

    public static String PasswordHashing(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean PasswordChecking(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
