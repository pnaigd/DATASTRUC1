package com.pnaigd.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Logger {

    private static final String LOG_FILE = "data/output.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withLocale(Locale.forLanguageTag("en-PH"));

    public static void log(String tag, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = "[" + timestamp + "] [" + tag.toUpperCase() + "] " + message;
        System.out.println();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(entry);
            bw.newLine();
        } catch (IOException e) {
            // prints error if something goes wrong
            System.out.println("Logger error: " + e.getMessage());
        }
    }

    // for the main module
    public static void pagsm(String message) {
        log("PAGSM", message);
    }

    // for the game stop module
    public static void gamestop(String message) {
        log("GAMESTOP", message);
    }

    // for the quiz module
    public static void quizist(String message) {
        log("QUIZIST", message);
    }

    // for user management
    public static void usermgr(String message) {
        log("USERMGR", message);
    }
}