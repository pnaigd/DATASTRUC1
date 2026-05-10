package MIDTERMS;

import java.io.*;
import java.util.*;

public class UserManager {

    private static final String FILE_PATH = "data/users.json";

    public static class User {
        String username;
        String password;
        int highScore;

        User(String username, String password, int highScore) {
            this.username = username;
            this.password = password;
            this.highScore = highScore;
        }
    }

    // ── Simple hand-rolled JSON helpers ───────────────────────────────────────

    private static String jsonEscape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String parseString(String line, String key) {
        String token = "\"" + key + "\":";
        int idx = line.indexOf(token);
        if (idx < 0) return "";
        int start = line.indexOf('"', idx + token.length()) + 1;
        int end   = line.indexOf('"', start);
        return (start > 0 && end > start) ? line.substring(start, end) : "";
    }

    private static int parseInt(String line, String key) {
        String token = "\"" + key + "\":";
        int idx = line.indexOf(token);
        if (idx < 0) return 0;
        String rest = line.substring(idx + token.length()).trim()
                          .replaceAll(",.*", "").replaceAll("}", "").trim();
        try { return Integer.parseInt(rest); } catch (NumberFormatException e) { return 0; }
    }

    // ── Persistence ───────────────────────────────────────────────────────────

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return users;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String username = "", password = "";
            int highScore = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String t = line.trim();
                if (t.contains("\"username\""))  username  = parseString(t, "username");
                if (t.contains("\"password\""))  password  = parseString(t, "password");
                if (t.contains("\"highScore\"")) highScore = parseInt(t,    "highScore");
                if (t.startsWith("}") && !username.isEmpty()) {
                    users.add(new User(username, password, highScore));
                    username = ""; password = ""; highScore = 0;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        new File("data").mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println("[");
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                pw.println("  {");
                pw.println("    \"username\": \"" + jsonEscape(u.username) + "\",");
                pw.println("    \"password\": \"" + jsonEscape(u.password) + "\",");
                pw.println("    \"highScore\": " + u.highScore);
                pw.print("  }");
                pw.println(i < users.size() - 1 ? "," : "");
            }
            pw.println("]");
        } catch (IOException e) {
            System.out.println("Could not save users: " + e.getMessage());
            Logger.usermgr("Save error: " + e.getMessage());
        }
    }

    // ── Core operations ───────────────────────────────────────────────────────

    public static User findUser(List<User> users, String username) {
        for (User u : users)
            if (u.username.equalsIgnoreCase(username)) return u;
        return null;
    }

    public static boolean register(String username, String password) {
        List<User> users = loadUsers();
        if (findUser(users, username) != null) {
            System.out.println("Username already taken.");
            Logger.usermgr("Registration failed — username already exists: \"" + username + "\"");
            return false;
        }
        users.add(new User(username, password, 0));
        saveUsers(users);
        System.out.println("Account created! Welcome, " + username + "!");
        Logger.usermgr("New account registered: \"" + username + "\"");
        return true;
    }

    public static boolean login(String username, String password) {
        List<User> users = loadUsers();
        User user = findUser(users, username);
        if (user == null) {
            System.out.println("User not found.");
            Logger.usermgr("Login failed — user not found: \"" + username + "\"");
            return false;
        }
        if (!user.password.equals(password)) {
            System.out.println("Wrong password.");
            Logger.usermgr("Login failed — wrong password for: \"" + username + "\"");
            return false;
        }
        System.out.println("Welcome back, " + username + "! High Score: " + user.highScore);
        Logger.usermgr("Login successful: \"" + username + "\" (High Score: " + user.highScore + ")");
        return true;
    }

    public static void editPlayer(String loggedInUser, Scanner sc) {
        List<User> users = loadUsers();
        User user = findUser(users, loggedInUser);
        if (user == null) { System.out.println("User not found."); return; }

        String oldName = user.username;
        System.out.print("New username (blank = keep '" + user.username + "'): ");
        String newName = sc.nextLine().trim();
        System.out.print("New password (blank = keep current): ");
        String newPass = sc.nextLine().trim();

        boolean changed = false;
        if (!newName.isEmpty()) { user.username = newName; changed = true; }
        if (!newPass.isEmpty()) { user.password = newPass; changed = true; }

        if (changed) {
            saveUsers(users);
            System.out.println("Profile updated.");
            Logger.usermgr(String.format("Profile edited — OldUsername:\"%s\" → NewUsername:\"%s\" PasswordChanged:%s",
                    oldName, user.username, !newPass.isEmpty()));
        } else {
            System.out.println("No changes made.");
            Logger.usermgr("Profile edit — no changes made for: \"" + loggedInUser + "\"");
        }
    }

    public static boolean deletePlayer(String loggedInUser) {
        List<User> users = loadUsers();
        boolean removed = users.removeIf(u -> u.username.equalsIgnoreCase(loggedInUser));
        if (removed) {
            saveUsers(users);
            System.out.println("Account deleted.");
            Logger.usermgr("Account deleted: \"" + loggedInUser + "\"");
        } else {
            System.out.println("User not found.");
            Logger.usermgr("Delete failed — user not found: \"" + loggedInUser + "\"");
        }
        return removed;
    }

    public static void listPlayers(Scanner sc) {
        List<User> users = loadUsers();
        if (users.isEmpty()) { System.out.println("No players found."); return; }
        Logger.usermgr("Player list viewed (" + users.size() + " players)");

        Stack<Integer> back = new Stack<>();
        int current = 0;
        while (true) {
            User u = users.get(current);
            System.out.println("\n--- Player " + (current + 1) + " of " + users.size() + " ---");
            System.out.println("Username  : " + u.username);
            System.out.println("High Score: " + u.highScore);
            System.out.println("[N] Next  [B] Back  [X] Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine().trim().toUpperCase();
            switch (input) {
                case "N" -> { if (current < users.size() - 1) { back.push(current); current++; } else System.out.println("No more players."); }
                case "B" -> { if (!back.isEmpty()) current = back.pop(); else System.out.println("Already at the first player."); }
                case "X" -> { return; }
                default  -> System.out.println("Invalid.");
            }
        }
    }

    public static void searchPlayer(Scanner sc) {
        List<User> users = loadUsers();
        System.out.print("Search username: ");
        String query = sc.nextLine().trim().toLowerCase();
        List<User> results = new ArrayList<>();
        for (User u : users)
            if (u.username.toLowerCase().contains(query)) results.add(u);

        Logger.usermgr("Player search — keyword:\"" + query + "\" results:" + results.size());
        if (results.isEmpty()) { System.out.println("No players matched."); return; }

        Stack<Integer> back = new Stack<>();
        int current = 0;
        while (true) {
            User u = results.get(current);
            System.out.println("\n--- Result " + (current + 1) + " of " + results.size() + " ---");
            System.out.println("Username  : " + u.username);
            System.out.println("High Score: " + u.highScore);
            System.out.println("[N] Next  [B] Back  [X] Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine().trim().toUpperCase();
            switch (input) {
                case "N" -> { if (current < results.size() - 1) { back.push(current); current++; } else System.out.println("No more results."); }
                case "B" -> { if (!back.isEmpty()) current = back.pop(); else System.out.println("Already at first result."); }
                case "X" -> { return; }
                default  -> System.out.println("Invalid.");
            }
        }
    }

    public static void leaderboard() {
        List<User> users = loadUsers();
        if (users.isEmpty()) { System.out.println("No players yet."); return; }
        users.sort((a, b) -> b.highScore - a.highScore);
        System.out.println("\n======= LEADERBOARD =======");
        for (int i = 0; i < users.size(); i++)
            System.out.printf("%2d. %-15s %d pts%n", i + 1, users.get(i).username, users.get(i).highScore);
        System.out.println("===========================");
        Logger.usermgr("Leaderboard viewed (" + users.size() + " players)");
    }

    public static void updateHighScore(String username, int score) {
        List<User> users = loadUsers();
        User user = findUser(users, username);
        if (user != null && score > user.highScore) {
            int old = user.highScore;
            user.highScore = score;
            saveUsers(users);
            System.out.println("New high score saved: " + score + "!");
            Logger.usermgr(String.format("High score updated — User:\"%s\" OldScore:%d → NewScore:%d",
                    username, old, score));
        } else if (user != null) {
            Logger.usermgr(String.format("Score not a high score — User:\"%s\" Score:%d (Best:%d)",
                    username, score, user.highScore));
        }
    }

    public static int getHighScore(String username) {
        List<User> users = loadUsers();
        User user = findUser(users, username);
        return user != null ? user.highScore : 0;
    }
}
