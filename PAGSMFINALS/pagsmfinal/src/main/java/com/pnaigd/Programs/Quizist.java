package com.pnaigd.Programs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.pnaigd.Service.Logger;
import com.pnaigd.Service.UserManager;

public class Quizist {
    public static Scanner sc = new Scanner(System.in);
    public static List<String> mcList = new ArrayList<>();
    public static String loggedInUser = null;
    public static final String FILE_PATH = "data/multiplechoice.txt";

    public static void run() {
        loadQuestions();
        Logger.quizist("Quizist opened. Questions loaded: " + (mcList.size() / 5));
        while (true) {
            String status = loggedInUser != null ? " (" + loggedInUser + ")" : "";
            System.out.print("\nQUIZIST" + status + """

                    [1] Player Registration
                    [2] Question Bank
                    [3] Play
                    [4] Leaderboard
                    [5] Back to Main Menu
                    CHOICE:\s""");
            String choice = sc.next();

            switch (choice) {
                case "1" -> registrationMenu();
                case "2" -> questionBankMenu();
                case "3" -> play();
                case "4" -> UserManager.leaderboard();
                case "5" -> {
                    saveQuestions();
                    System.out.println("Returning to main menu...");
                    Logger.quizist("Quizist closed. Returned to main menu.");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice.");
                    Logger.quizist("Invalid menu choice: " + choice);
                }
            }
        }
    }

    public static void loadQuestions() {
        mcList.clear();
        File file = new File(FILE_PATH);
        if (!file.exists())
            return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    mcList.add(line.trim());
        } catch (IOException e) {
            System.out.println("Could not load questions: " + e.getMessage());
        }
    }

    public static void saveQuestions() {
        new File("data").mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String line : mcList)
                pw.println(line);
        } catch (IOException e) {
            System.out.println("Could not save questions: " + e.getMessage());
            Logger.quizist("Save error: " + e.getMessage());
        }
    }

    public static void play() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to play!");
            Logger.quizist("Play attempt blocked — no user logged in.");
            return;
        }
        int total = mcList.size() / 5;
        if (total == 0) {
            System.out.println("No questions available.");
            Logger.quizist("Play attempt blocked — no questions in bank.");
            return;
        }

        Logger.quizist("Game started — User:\"" + loggedInUser + "\" Questions:" + total);

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < total; i++)
            indices.add(i);
        Collections.shuffle(indices);

        String[] answers = new String[total];
        int current = 0;
        Stack<Integer> back = new Stack<>();

        while (true) {
            int qi = indices.get(current);
            int base = qi * 5;
            System.out.println("\nQ" + (current + 1) + " of " + total + ": " + mcList.get(base));
            System.out.println("[A] " + mcList.get(base + 1));
            System.out.println("[B] " + mcList.get(base + 2));
            System.out.println("[C] " + mcList.get(base + 3));
            if (answers[current] != null)
                System.out.println("Your answer: " + answers[current]);
            System.out.println("[1] Next  [2] Back  [3] Finish");
            System.out.print("Answer/Choice: ");
            String input = sc.next().toUpperCase();

            if (input.equals("A") || input.equals("B") || input.equals("C")) {
                answers[current] = input;
                Logger.quizist(String.format("Answer given — User:\"%s\" Q%d answered [%s]",
                        loggedInUser, current + 1, input));
            } else if (input.equals("1")) {
                if (current < total - 1) {
                    back.push(current);
                    current++;
                } else
                    System.out.println("Already on last question.");
            } else if (input.equals("2")) {
                if (!back.isEmpty())
                    current = back.pop();
                else
                    System.out.println("No previous question.");
            } else if (input.equals("3")) {
                break;
            } else {
                System.out.println("Invalid.");
            }
        }

        int score = 0;
        System.out.println("\n--- Results ---");
        for (int i = 0; i < total; i++) {
            int qi = indices.get(i);
            String correct = mcList.get(qi * 5 + 4);
            String given = answers[i] != null ? answers[i] : "-";
            boolean ok = correct.equals(given);
            if (ok)
                score++;
            System.out.println(
                    "Q" + (i + 1) + ": You answered [" + given + "] | Correct: [" + correct + "] " + (ok ? "V" : "X"));
        }
        System.out.println("Score: " + score + "/" + total);
        Logger.quizist(String.format("Game finished — User:\"%s\" Score:%d/%d",
                loggedInUser, score, total));
        UserManager.updateHighScore(loggedInUser, score);
    }

    public static void registrationMenu() {
        while (true) {
            String status = loggedInUser != null ? " (Logged in: " + loggedInUser + ")" : "";
            System.out.print("""

                    PLAYER REGISTRATION""" + status + """

                    [1] Login
                    [2] Register
                    [3] Edit Profile
                    [4] Delete Account
                    [5] List Players
                    [6] Search Player
                    [7] Leaderboard
                    [8] Logout
                    [9] Back
                    CHOICE:\s""");
            String input = sc.next();
            sc.nextLine();

            switch (input) {
                case "1" -> {
                    if (loggedInUser != null) {
                        System.out.println("Already logged in as " + loggedInUser);
                        break;
                    }
                    System.out.print("Username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String p = sc.nextLine().trim();
                    if (UserManager.login(u, p))
                        loggedInUser = u;
                }
                case "2" -> {
                    System.out.print("Username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String p = sc.nextLine().trim();
                    UserManager.register(u, p);
                }
                case "3" -> {
                    if (loggedInUser == null) {
                        System.out.println("Login first.");
                        break;
                    }
                    UserManager.editPlayer(loggedInUser, sc);
                }
                case "4" -> {
                    if (loggedInUser == null) {
                        System.out.println("Login first.");
                        break;
                    }
                    System.out.print("Are you sure? (yes/no): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
                        UserManager.deletePlayer(loggedInUser);
                        loggedInUser = null;
                    }
                }
                case "5" -> UserManager.listPlayers(sc);
                case "6" -> UserManager.searchPlayer(sc);
                case "7" -> UserManager.leaderboard();
                case "8" -> {
                    if (loggedInUser != null) {
                        Logger.quizist("User logged out: \"" + loggedInUser + "\"");
                        System.out.println("Logged out.");
                        loggedInUser = null;
                    } else
                        System.out.println("Not logged in.");
                }
                case "9" -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void questionBankMenu() {
        while (true) {
            System.out.print("""

                    QUESTION BANK
                    [1] Add Question
                    [2] Edit Question
                    [3] Delete Question
                    [4] List Questions
                    [5] Search Questions
                    [6] Back
                    CHOICE:\s""");
            String input = sc.next();
            sc.nextLine();

            switch (input) {
                case "1" -> addQuestion();
                case "2" -> editQuestion();
                case "3" -> deleteQuestion();
                case "4" -> listQuestions();
                case "5" -> searchQuestions();
                case "6" -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void addQuestion() {
        System.out.print("Question: ");
        String q = sc.nextLine().trim();
        System.out.print("Option A: ");
        String a = sc.nextLine().trim();
        System.out.print("Option B: ");
        String b = sc.nextLine().trim();
        System.out.print("Option C: ");
        String c = sc.nextLine().trim();
        System.out.print("Answer (A/B/C): ");
        String ans = sc.nextLine().trim().toUpperCase();
        if (!ans.equals("A") && !ans.equals("B") && !ans.equals("C")) {
            System.out.println("Invalid answer key. Question not saved.");
            Logger.quizist("Add question failed — invalid answer key: \"" + ans + "\"");
            return;
        }
        mcList.add(q);
        mcList.add(a);
        mcList.add(b);
        mcList.add(c);
        mcList.add(ans);
        saveQuestions();
        System.out.println("Question added!");
        Logger.quizist("Question added — \"" + q + "\" Answer:" + ans + " (Total:" + (mcList.size() / 5) + ")");
    }

    public static void editQuestion() {
        int total = mcList.size() / 5;
        if (total == 0) {
            System.out.println("No questions to edit.");
            return;
        }
        listQuestions();
        System.out.print("Enter question number to edit: ");
        int num;
        try {
            num = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }
        if (num < 1 || num > total) {
            System.out.println("Out of range.");
            return;
        }

        int base = (num - 1) * 5;
        String oldQ = mcList.get(base);
        System.out.println("Editing Q" + num + " (blank = keep current):");
        System.out.print("Question [" + mcList.get(base) + "]: ");
        String q = sc.nextLine().trim();
        System.out.print("Option A [" + mcList.get(base + 1) + "]: ");
        String a = sc.nextLine().trim();
        System.out.print("Option B [" + mcList.get(base + 2) + "]: ");
        String b = sc.nextLine().trim();
        System.out.print("Option C [" + mcList.get(base + 3) + "]: ");
        String c = sc.nextLine().trim();
        System.out.print("Answer   [" + mcList.get(base + 4) + "]: ");
        String ans = sc.nextLine().trim().toUpperCase();

        if (!q.isEmpty())
            mcList.set(base, q);
        if (!a.isEmpty())
            mcList.set(base + 1, a);
        if (!b.isEmpty())
            mcList.set(base + 2, b);
        if (!c.isEmpty())
            mcList.set(base + 3, c);
        if (ans.equals("A") || ans.equals("B") || ans.equals("C"))
            mcList.set(base + 4, ans);

        saveQuestions();
        System.out.println("Question updated!");
        Logger.quizist(
                "Question edited — Q" + num + " OldText:\"" + oldQ + "\" → NewText:\"" + mcList.get(base) + "\"");
    }

    public static void deleteQuestion() {
        int total = mcList.size() / 5;
        if (total == 0) {
            System.out.println("No questions to delete.");
            return;
        }
        listQuestions();
        System.out.print("Enter question number to delete: ");
        int num;
        try {
            num = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }
        if (num < 1 || num > total) {
            System.out.println("Out of range.");
            return;
        }

        int base = (num - 1) * 5;
        String deletedQ = mcList.get(base);
        System.out.print("Delete \"" + deletedQ + "\"? (yes/no): ");
        if (!sc.nextLine().trim().equalsIgnoreCase("yes")) {
            System.out.println("Cancelled.");
            Logger.quizist("Question delete cancelled — Q" + num + ": \"" + deletedQ + "\"");
            return;
        }
        for (int i = 0; i < 5; i++)
            mcList.remove(base);
        saveQuestions();
        System.out.println("Question deleted.");
        Logger.quizist("Question deleted — Q" + num + ": \"" + deletedQ + "\" (Remaining:" + (mcList.size() / 5) + ")");
    }

    public static void listQuestions() {
        int total = mcList.size() / 5;
        if (total == 0) {
            System.out.println("No questions in bank.");
            return;
        }
        Logger.quizist("Question list viewed (" + total + " questions)");

        Stack<Integer> back = new Stack<>();
        int current = 0;
        while (true) {
            int base = current * 5;
            System.out.println("\n--- Q" + (current + 1) + " of " + total + " ---");
            System.out.println("Q: " + mcList.get(base));
            System.out.println("A: " + mcList.get(base + 1));
            System.out.println("B: " + mcList.get(base + 2));
            System.out.println("C: " + mcList.get(base + 3));
            System.out.println("Answer: " + mcList.get(base + 4));
            System.out.println("[N] Next  [B] Back  [X] Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine().trim().toUpperCase();
            switch (input) {
                case "N" -> {
                    if (current < total - 1) {
                        back.push(current);
                        current++;
                    } else
                        System.out.println("Last question.");
                }
                case "B" -> {
                    if (!back.isEmpty())
                        current = back.pop();
                    else
                        System.out.println("Already at first question.");
                }
                case "X" -> {
                    return;
                }
                default -> System.out.println("Invalid.");
            }
        }
    }

    public static void searchQuestions() {
        System.out.print("Search keyword: ");
        String query = sc.nextLine().trim().toLowerCase();
        int total = mcList.size() / 5;
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < total; i++)
            if (mcList.get(i * 5).toLowerCase().contains(query))
                results.add(i);

        Logger.quizist("Question search — keyword:\"" + query + "\" results:" + results.size());
        if (results.isEmpty()) {
            System.out.println("No questions matched.");
            return;
        }

        Stack<Integer> back = new Stack<>();
        int current = 0;
        while (true) {
            int qi = results.get(current);
            int base = qi * 5;
            System.out.println("\n--- Result " + (current + 1) + " of " + results.size() + " (Q" + (qi + 1) + ") ---");
            System.out.println("Q: " + mcList.get(base));
            System.out.println("A: " + mcList.get(base + 1));
            System.out.println("B: " + mcList.get(base + 2));
            System.out.println("C: " + mcList.get(base + 3));
            System.out.println("Answer: " + mcList.get(base + 4));
            System.out.println("[N] Next  [B] Back  [X] Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine().trim().toUpperCase();
            switch (input) {
                case "N" -> {
                    if (current < results.size() - 1) {
                        back.push(current);
                        current++;
                    } else
                        System.out.println("No more results.");
                }
                case "B" -> {
                    if (!back.isEmpty())
                        current = back.pop();
                    else
                        System.out.println("Already at first result.");
                }
                case "X" -> {
                    return;
                }
                default -> System.out.println("Invalid.");
            }
        }
    }
}
