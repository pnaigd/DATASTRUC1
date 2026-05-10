package MIDTERMS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Logger {

    private static final String LOG_FILE = "output.txt";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                             .withLocale(Locale.forLanguageTag("en-PH"));

    /**
     * Writes a timestamped, tagged log entry.
     * Format: [yyyy-MM-dd HH:mm:ss] [TAG] message
     *
     * @param tag     short label for the module, e.g. "PAGSM", "GAMESTOP", "QUIZIST", "USERMGR"
     * @param message what happened
     */
    public static void log(String tag, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = "[" + timestamp + "] [" + tag.toUpperCase() + "] " + message;
        System.out.println();   // blank line separator in console is optional; remove if noisy
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(entry);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Logger error: " + e.getMessage());
        }
    }

    // ── Convenience wrappers so each class doesn't need to repeat its tag ──

    public static void pagsm(String message)    { log("PAGSM",    message); }
    public static void gamestop(String message) { log("GAMESTOP", message); }
    public static void quizist(String message)  { log("QUIZIST",  message); }
    public static void usermgr(String message)  { log("USERMGR",  message); }
}
