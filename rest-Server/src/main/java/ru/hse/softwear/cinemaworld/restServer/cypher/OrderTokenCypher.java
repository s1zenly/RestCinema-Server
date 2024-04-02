package ru.hse.softwear.cinemaworld.restServer.cypher;


import ru.hse.softwear.cinemaworld.restServer.util.AES.DecryptUtil;
import ru.hse.softwear.cinemaworld.restServer.util.AES.EncryptUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class OrderTokenCypher {

    private static final String FORMATTER_PATTERN = "yyyy-MM-ddHH:mm:ss";

    public static String encoder(Long userId, Long sessionId) throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();

        String data = localDateTime.format(DateTimeFormatter.ofPattern(FORMATTER_PATTERN)) + " "
                + sessionId + " " + userId;

        return EncryptUtil.encrypt(data);
    }

    public static Map<String, Object> decoder(String token) throws Exception {
        Map<String, Object> result = new HashMap<>();

        String data = DecryptUtil.decrypt(token);
        String[] info = data.split(" ");

        String date = info[0];
        String sessionId = info[1];
        String userId = info[2];

        result.put("date", LocalDateTime.parse(date, DateTimeFormatter.ofPattern(FORMATTER_PATTERN)));
        result.put("sessionId", Long.parseLong(sessionId));
        result.put("userId", Long.parseLong(userId));

        return result;
    }
}
