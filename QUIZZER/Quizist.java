package QUIZZER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Quizist {
    public static Scanner sc = new Scanner(System.in);
    public static List<String> mcList = new ArrayList<>();

    public static void loadQuestions() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("QUIZZER/mulitplechoice.txt"));
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    mcList.add(line.trim());
            br.close();
        } catch (IOException e) {
            System.out.println("Could not load questions: " + e.getMessage());
        }
    }

    public static void play() {
        int total = mcList.size() / 5;
        String[] answers = new String[total];
        int current = 0;
        Stack<Integer> back = new Stack<>();
        Stack<Integer> next = new Stack<>();

        while (true) {
            int base = current * 5;
            System.out.println("\nQ" + (current + 1) + ": " + mcList.get(base));
            System.out.println("[A] " + mcList.get(base + 1));
            System.out.println("[B] " + mcList.get(base + 2));
            System.out.println("[C] " + mcList.get(base + 3));
            if (answers[current] != null)
                System.out.println("Your answer: " + answers[current]);
            System.out.println("[1] Back, [2] Next, [3] Finish");
            System.out.print("Answer (A/B/C):");   
            String input = sc.next().toUpperCase();

            if (input.equals("A") || input.equals("B") || input.equals("C")) {
                answers[current] = input;
            } else if (input.equals("2") && current < total - 1) {
                back.push(current);
                next.clear();
                current++;
            } else if (input.equals("1") && !back.isEmpty()) {
                next.push(current);
                current = back.pop();
            } else if (input.equals("3")) {
                break;
            } else {
                System.out.println("Invalid.");
            }
        }

        int score = 0;
        for (int i = 0; i < total; i++) {
            String correct = mcList.get(i * 5 + 4);
            if (correct.equals(answers[i])) score++;
        }
        System.out.println("Score: " + score + "/" + total);
    }

    public static void main(String[] args) {
        loadQuestions();
        while (true) {
            System.out.print("""
                    MENU
                    [1] REGISTRATION
                    [2] PLAY
                    [3] EXIT
                    CHOICE: """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("""
                            PLAYER REGISTRATION
                            [1] LOGIN
                            [2] NEW PLAYER
                            [3] BACK
                            CHOICE: """);
                    int user = sc.nextInt();
                    switch (user) {
                        case 1 -> System.out.println("LOGIN");
                        case 2 -> System.out.println("NEW PLAYER");
                        case 3 -> System.out.println("BACK TO MAIN MENU");
                        default -> System.out.println("Invalid choice.");
                    }
                }
                case 2 -> play();
                case 3 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}