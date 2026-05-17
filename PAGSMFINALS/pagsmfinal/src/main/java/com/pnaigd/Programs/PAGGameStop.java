package com.pnaigd.Programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.pnaigd.Service.Logger;

public class PAGGameStop {

    // Inner class to encapsulate Game data neatly
    public static class Game {
        private String name;
        private String genre;
        private String year;
        private String developer;
        private String platform;

        public Game(String name, String genre, String year, String developer, String platform) {
            this.name = name.trim().isEmpty() ? "Unknown" : name;
            this.genre = genre.trim().isEmpty() ? "Unknown" : genre;
            this.year = year.trim().isEmpty() ? "N/A" : year;
            this.developer = developer.trim().isEmpty() ? "Unknown" : developer;
            this.platform = platform.trim().isEmpty() ? "Unknown" : platform;
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
        public String getDeveloper() { return developer; }
        public void setDeveloper(String developer) { this.developer = developer; }
        public String getPlatform() { return platform; }
        public void setPlatform(String platform) { this.platform = platform; }
    }

    private static final String FILE_PATH = "data/gameList.txt";
    public static List<Game> gameList = new ArrayList<>();
    public static final Scanner sc = new Scanner(System.in);

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
            sc.nextLine(); // Clear buffer

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
        System.out.println("\n<<< ADD/INSERT GAME >>>");
        System.out.println("Current games in list: " + gameList.size());
        System.out.print("Insert at which position? (1 - " + (gameList.size() + 1) + "): ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid position input.");
            sc.next();
            return;
        }
        int position = sc.nextInt();
        sc.nextLine(); // Clear buffer

        // Bound checking to prevent IndexOutOfBoundsException
        int insertIndex = Math.max(0, Math.min(position - 1, gameList.size()));

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

        Game newGame = new Game(name, genre, year, dev, plat);
        gameList.add(insertIndex, newGame);

        save();
        System.out.println("Game inserted successfully!");
        Logger.gamestop(String.format(
                "Game added at position %d — Name:\"%s\" Genre:%s Year:%s Dev:\"%s\" Platform:%s",
                position, newGame.getName(), newGame.getGenre(), newGame.getYear(), newGame.getDeveloper(), newGame.getPlatform()));
    }

    public void searchGame() {
        System.out.print("\nEnter Game Name to search: ");
        String keyword = sc.nextLine().toLowerCase().trim();
        Logger.gamestop("Search performed — keyword:\"" + keyword + "\"");
        
        boolean found = false;
        int id = 1;
        
        for (Game game : gameList) {
            if (game.getName().toLowerCase().contains(keyword)) {
                if (!found) {
                    System.out.println("\n--- SEARCH RESULTS ---");
                    printHeader();
                }
                printRow(id, game);
                found = true;
            }
            id++;
        }
        
        if (!found) {
            System.out.println("No matching games found.");
            Logger.gamestop("Search found no results for: \"" + keyword + "\"");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void editGame() {
        displayList();
        if (gameList.isEmpty()) return;

        System.out.print("\nEnter ID of the game to EDIT: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID format.");
            sc.next();
            return;
        }
        int id = sc.nextInt();
        sc.nextLine(); // Clear buffer

        int index = id - 1;
        if (index < 0 || index >= gameList.size()) {
            System.out.println("Invalid ID.");
            Logger.gamestop("Edit failed — invalid ID: " + id);
            return;
        }

        Game game = gameList.get(index);
        String oldName = game.getName();

        System.out.print("New Name (leave blank to keep [" + game.getName() + "]): ");
        String name = sc.nextLine();
        System.out.print("New Genre (leave blank to keep [" + game.getGenre() + "]): ");
        String genre = sc.nextLine();
        System.out.print("New Year (leave blank to keep [" + game.getYear() + "]): ");
        String year = sc.nextLine();
        System.out.print("New Developer (leave blank to keep [" + game.getDeveloper() + "]): ");
        String dev = sc.nextLine();
        System.out.print("New Platform (leave blank to keep [" + game.getPlatform() + "]): ");
        String plat = sc.nextLine();

        if (!name.trim().isEmpty()) game.setName(name);
        if (!genre.trim().isEmpty()) game.setGenre(genre);
        if (!year.trim().isEmpty()) game.setYear(year);
        if (!dev.trim().isEmpty()) game.setDeveloper(dev);
        if (!plat.trim().isEmpty()) game.setPlatform(plat);

        save();
        System.out.println("Game updated successfully!");
        Logger.gamestop(String.format(
                "Game edited — ID:%d OldName:\"%s\" → Name:\"%s\" Genre:\"%s\" Year:\"%s\" Dev:\"%s\" Platform:\"%s\"",
                id, oldName, game.getName(), game.getGenre(), game.getYear(), game.getDeveloper(), game.getPlatform()));
    }

    public void deleteGame() {
        displayList();
        if (gameList.isEmpty()) return;

        System.out.print("\nEnter ID of the game to DELETE: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID format.");
            sc.next();
            return;
        }
        int id = sc.nextInt();
        sc.nextLine(); // Clear buffer

        int index = id - 1;
        if (index < 0 || index >= gameList.size()) {
            System.out.println("Invalid ID.");
            Logger.gamestop("Delete failed — invalid ID: " + id);
            return;
        }

        Game deletedGame = gameList.remove(index);
        save();
        System.out.println("Game deleted successfully.");
        Logger.gamestop("Game deleted — ID:" + id + " Name:\"" + deletedGame.getName() + "\"");
    }

    public void sortGames() {
        if (gameList.isEmpty()) {
            System.out.println("\n[!] THE LIST IS EMPTY. NOTHING TO SORT.");
            return;
        }

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
        sc.nextLine(); // Clear buffer

        String sortDesc;
        switch (choice) {
            case 1 -> {
                gameList.sort(Comparator.comparing(g -> g.getName().toLowerCase()));
                sortDesc = "Name A-Z";
                displayList();
            }
            case 2 -> {
                gameList.sort((g1, g2) -> g2.getName().toLowerCase().compareTo(g1.getName().toLowerCase()));
                sortDesc = "Name Z-A";
                displayList();
            }
            case 3 -> {
                gameList.sort(Comparator.comparing(Game::getYear));
                sortDesc = "Year Ascending";
                displayList();
            }
            case 4 -> {
                gameList.sort((g1, g2) -> g2.getYear().compareTo(g1.getYear()));
                sortDesc = "Year Descending";
                displayList();
            }
            default -> {
                System.out.println("Invalid choice.");
                Logger.gamestop("Sort failed — invalid choice: " + choice);
                return;
            }
        }

        save();
        System.out.println("Sorted: " + sortDesc);
        Logger.gamestop("List sorted by: " + sortDesc);
    }

    public static void read() {
        gameList.clear();
        File file = new File(FILE_PATH);
        
        // Handle directory creation automatically for Maven project structures
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String name;
            // Read 5 lines sequentially for each game object safely
            while ((name = br.readLine()) != null) {
                if (name.trim().isEmpty()) continue; 
                
                String genre = br.readLine();
                String year = br.readLine();
                String dev = br.readLine();
                String plat = br.readLine();

                // Fallbacks if file structure gets partially cut off
                gameList.add(new Game(
                    name, 
                    genre != null ? genre : "Unknown", 
                    year != null ? year : "N/A", 
                    dev != null ? dev : "Unknown", 
                    plat != null ? plat : "Unknown"
                ));
            }
        } catch (IOException e) {
            System.out.println("Starting with a fresh list (no file found).");
        }
    }

    public static void save() {
        File file = new File(FILE_PATH);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Game game : gameList) {
                bw.write(game.getName()); bw.newLine();
                bw.write(game.getGenre()); bw.newLine();
                bw.write(game.getYear()); bw.newLine();
                bw.write(game.getDeveloper()); bw.newLine();
                bw.write(game.getPlatform()); bw.newLine();
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
        for (Game game : gameList) {
            printRow(count++, game);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printHeader() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV", "PLATFORM");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printRow(int id, Game game) {
        System.out.printf("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, game.getName(), game.getGenre(), game.getYear(), game.getDeveloper(), game.getPlatform());
    }
}