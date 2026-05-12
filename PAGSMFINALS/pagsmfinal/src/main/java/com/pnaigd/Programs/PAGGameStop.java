package com.pnaigd.Programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.pnaigd.Service.Logger;

public class PAGGameStop {

    public static List<String> gameList = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new PAGGameStop().run();
    }

    public void run() {
        read();
        Logger.gamestop("GameStop opened.");
        while (true) {
            System.out.println("""
                    \nMENU
                    [1] ADD
                    [2] SEARCH
                    [3] EDIT
                    [4] DELETE
                    [5] SORT
                    [6] LIST
                    [0] EXIT
                    """);
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("PLEASE ENTER A NUMBER.");
                sc.next();
                continue;
            }

            int user = sc.nextInt();
            sc.nextLine();

            switch (user) {
                case 1 -> addGame();
                case 2 -> searchGame();
                case 3 -> editGame();
                case 4 -> deleteGame();
                case 5 -> sortGames();
                case 6 -> displayList();
                case 0 -> {
                    System.out.println("Exiting PAG GameStop...");
                    Logger.gamestop("GameStop closed by user.");
                    return;
                }
                default -> {
                    System.out.println("INVALID INPUT");
                    Logger.gamestop("Invalid menu choice: " + user);
                }
            }
        }
    }

    public void addGame() {
        int totalGames = gameList.size() / 5;
        System.out.println("\n<<< ADD/INSERT GAME >>>");
        System.out.println("Current games in list: " + totalGames);
        System.out.print("Insert at which position? (1 - " + (totalGames + 1) + "): ");

        int position = sc.nextInt();
        sc.nextLine();
        int insertIndex = Math.max(0, Math.min((position - 1) * 5, gameList.size()));

        System.out.print("Enter Game Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Game Genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter Game Year: ");
        String year = sc.nextLine();
        System.out.print("Enter Game Developer: ");
        String dev = sc.nextLine();
        System.out.print("Enter Game Platform: ");
        String plat = sc.nextLine();

        gameList.add(insertIndex, name);
        gameList.add(insertIndex + 1, genre);
        gameList.add(insertIndex + 2, year);
        gameList.add(insertIndex + 3, dev);
        gameList.add(insertIndex + 4, plat);

        save();
        System.out.println("Game inserted successfully!");
        Logger.gamestop(String.format(
                "Game added at position %d — Name:\"%s\" Genre:%s Year:%s Dev:\"%s\" Platform:%s",
                position, name, genre, year, dev, plat));
    }

    public void searchGame() {
        System.out.print("\nEnter Game Name to search: ");
        String keyword = sc.nextLine().toLowerCase();
        Logger.gamestop("Search performed — keyword:\"" + keyword + "\"");
        boolean found = false;
        System.out.println("\n--- SEARCH RESULTS ---");
        for (int i = 0; i < gameList.size(); i += 5) {
            if (gameList.get(i).toLowerCase().contains(keyword)) {
                printHeader();
                printRow((i / 5) + 1, i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching games found.");
            Logger.gamestop("Search found no results for: \"" + keyword + "\"");
        }
    }

    public void editGame() {
        displayList();
        System.out.print("\nEnter ID of the game to EDIT: ");
        int id = sc.nextInt();
        sc.nextLine();
        int index = (id - 1) * 5;
        if (index < 0 || index >= gameList.size()) {
            System.out.println("Invalid ID.");
            Logger.gamestop("Edit failed — invalid ID: " + id);
            return;
        }
        String oldName = gameList.get(index);

        System.out.print("New Name (leave blank to keep): ");
        String name = sc.nextLine();
        System.out.print("New Genre (leave blank to keep): ");
        String genre = sc.nextLine();
        System.out.print("New Year (leave blank to keep): ");
        String year = sc.nextLine();
        System.out.print("New Developer (leave blank to keep): ");
        String dev = sc.nextLine();
        System.out.print("New Platform (leave blank to keep): ");
        String plat = sc.nextLine();

        if (!name.isEmpty())
            gameList.set(index, name);
        if (!genre.isEmpty())
            gameList.set(index + 1, genre);
        if (!year.isEmpty())
            gameList.set(index + 2, year);
        if (!dev.isEmpty())
            gameList.set(index + 3, dev);
        if (!plat.isEmpty())
            gameList.set(index + 4, plat);

        save();
        System.out.println("Game updated successfully!");
        Logger.gamestop(String.format(
                "Game edited — ID:%d OldName:\"%s\" → Name:\"%s\" Genre:\"%s\" Year:\"%s\" Dev:\"%s\" Platform:\"%s\"",
                id, oldName,
                gameList.get(index), gameList.get(index + 1),
                gameList.get(index + 2), gameList.get(index + 3), gameList.get(index + 4)));
    }

    public void deleteGame() {
        displayList();
        System.out.print("\nEnter ID of the game to DELETE: ");
        int id = sc.nextInt();
        sc.nextLine();
        int index = (id - 1) * 5;
        if (index < 0 || index >= gameList.size()) {
            System.out.println("Invalid ID.");
            Logger.gamestop("Delete failed — invalid ID: " + id);
            return;
        }
        String deletedName = gameList.get(index);
        for (int i = 0; i < 5; i++)
            gameList.remove(index);
        save();
        System.out.println("Game deleted successfully.");
        Logger.gamestop("Game deleted — ID:" + id + " Name:\"" + deletedName + "\"");
    }

    public void sortGames() {
        System.out.println("""
                \nSORT BY:
                [1] Name (A-Z)
                [2] Name (Z-A)
                [3] Year (Ascending)
                [4] Year (Descending)
                """);
        System.out.print("Choice: ");
        if (!sc.hasNextInt()) {
            System.out.println("PLEASE ENTER A NUMBER.");
            sc.next();
            return;
        }
        int choice = sc.nextInt();
        sc.nextLine();

        List<String[]> temp = new ArrayList<>();
        for (int i = 0; i < gameList.size(); i += 5)
            temp.add(new String[] { gameList.get(i), gameList.get(i + 1), gameList.get(i + 2), gameList.get(i + 3),
                    gameList.get(i + 4) });

        String sortDesc;
        switch (choice) {
            case 1 -> {
                temp.sort(Comparator.comparing(a -> a[0].toLowerCase()));
                sortDesc = "Name A-Z";
            }
            case 2 -> {
                temp.sort((a, b) -> b[0].toLowerCase().compareTo(a[0].toLowerCase()));
                sortDesc = "Name Z-A";
            }
            case 3 -> {
                temp.sort(Comparator.comparing(a -> a[2]));
                sortDesc = "Year Ascending";
            }
            case 4 -> {
                temp.sort((a, b) -> b[2].compareTo(a[2]));
                sortDesc = "Year Descending";
            }
            default -> {
                System.out.println("Invalid choice.");
                Logger.gamestop("Sort failed — invalid choice: " + choice);
                return;
            }
        }

        gameList.clear();
        for (String[] game : temp)
            gameList.addAll(Arrays.asList(game));
        save();
        System.out.println("Sorted: " + sortDesc);
        Logger.gamestop("List sorted by: " + sortDesc);
    }

    public static void read() {
        gameList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/gameList.txt"))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    gameList.add(line);
        } catch (IOException e) {
            System.out.println("Starting with a fresh list (no file found).");
        }
    }

    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/gameList.txt"))) {
            for (String item : gameList) {
                bw.write(item);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            Logger.gamestop("Save error: " + e.getMessage());
        }
    }

    public void displayList() {
        if (gameList.isEmpty()) {
            System.out.println("\n[!] THE LIST IS CURRENTLY EMPTY.");
            return;
        }
        printHeader();
        int count = 1;
        for (int i = 0; i < gameList.size(); i += 5)
            printRow(count++, i);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printHeader() {
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV",
                "PLATFORM");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printRow(int id, int startIndex) {
        System.out.printf("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, gameList.get(startIndex), gameList.get(startIndex + 1),
                gameList.get(startIndex + 2), gameList.get(startIndex + 3), gameList.get(startIndex + 4));
    }
}
