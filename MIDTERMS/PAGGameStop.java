package MIDTERMS;

import java.io.*;
import java.util.*;

public class PAGGameStop {

    public static List<String> gameList = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new PAGGameStop().run();
    }

    public void run() {
        read();
<<<<<<< HEAD
        boolean run = true;
        while (run) {
=======
        while (true) {
>>>>>>> 6396d3c8d0010e5778d8f1e57cfc2adb0de359ae
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
<<<<<<< HEAD

            if (!sc.hasNextInt()) {
                System.out.println("PLEASE ENTER A NUMBER.");
                logs("PLEASE ENTER A NUMBER.");
                sc.next();
                continue;
            }

            int user = sc.nextInt();
            logs("""
                    \nMENU
                    [1] ADD
                    [2] SEARCH
                    [3] EDIT
                    [4] DELETE
                    [5] SORT
                    [6] LIST
                    [0] EXIT
                    """);
            logs("Choice: " + user);
            sc.nextLine();

            switch (user) {
                case 1 ->
                    addGame();
                case 2 ->
                    searchGame();
                case 3 ->
                    editGame();
                case 4 ->
                    deleteGame();
                case 5 ->
                    sortGames();
                case 6 ->
                    displayList();
                case 0 -> {
                    System.out.println("Exiting...");
                    logs("Exiting...");
                    run = false;
                    break;
                }
                default ->
                    System.out.println("INVALID INPUT");
            }
        }
=======

            if (!sc.hasNextInt()) {
                System.out.println("PLEASE ENTER A NUMBER.");
                logs("PLEASE ENTER A NUMBER.");
                sc.next();
                continue;
            }

            int user = sc.nextInt();
            logs("""
                    \nMENU
                    [1] ADD
                    [2] SEARCH
                    [3] EDIT
                    [4] DELETE
                    [5] SORT
                    [6] LIST
                    [0] EXIT
                    """);
            logs("Choice: " + user);
            sc.nextLine();

            switch (user) {
                case 1 ->
                    addGame();
                case 2 ->
                    searchGame();
                case 3 ->
                    editGame();
                case 4 ->
                    deleteGame();
                case 5 ->
                    sortGames();
                case 6 ->
                    displayList();
                case 0 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default ->
                    System.out.println("INVALID INPUT");
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
        logs("Enter Game Name: " + name);
        System.out.print("Enter Game Genre: ");
        String genre = sc.nextLine();
        logs("Enter Game Genre: " + genre);
        System.out.print("Enter Game Year: ");
        String year = sc.nextLine();
        logs("Enter Game Year: " + year);
        System.out.print("Enter Game Developer: ");
        String dev = sc.nextLine();
        logs("Enter Game Developer: " + dev);
        System.out.print("Enter Game Platform: ");
        String plat = sc.nextLine();
        logs("Enter Game Platform: " + plat);

        gameList.add(insertIndex, name);
        gameList.add(insertIndex + 1, genre);
        gameList.add(insertIndex + 2, year);
        gameList.add(insertIndex + 3, dev);
        gameList.add(insertIndex + 4, plat);

        save();
        System.out.println("Game inserted successfully!");
        logs("Game inserted successfully!");
    }

    public void searchGame() {
        System.out.print("\nEnter Game Name to search: ");
        String keyword = sc.nextLine().toLowerCase();
        logs("\nEnter Game Name to search: " + keyword);
        boolean found = false;

        System.out.println("\n--- SEARCH RESULTS ---");
        logs("\n--- SEARCH RESULTS ---");
        for (int i = 0; i < gameList.size(); i += 5) {
            if (gameList.get(i).toLowerCase().contains(keyword)) {
                printHeader();
                printRow((i / 5) + 1, i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching games found.");
            logs("No matching games found.");
        }
    }

    public void editGame() {
        displayList();
        System.out.print("\nEnter ID of the game to EDIT: ");
        int id = sc.nextInt();
        logs("\nEnter ID of the game to EDIT: " + id);
        sc.nextLine();

        int index = (id - 1) * 5;
        if (index >= 0 && index < gameList.size()) {
            System.out.print("New Name (leave blank to keep): ");
            String name = sc.nextLine();
            logs("New Name (leave blank to keep): " + name);
            if (!name.isEmpty()) {
                gameList.set(index, name);
            }

            System.out.print("New Genre (leave blank to keep): ");
            String genre = sc.nextLine();
            logs("New Genre (leave blank to keep): " + genre);
            if (!genre.isEmpty()) {
                gameList.set(index + 1, genre);
            }

            System.out.print("New Year (leave blank to keep): ");
            String year = sc.nextLine();
            logs("New Year (leave blank to keep): " + year);
            if (!year.isEmpty()) {
                gameList.set(index + 2, year);
            }

            System.out.print("New Developer (leave blank to keep): ");
            String dev = sc.nextLine();
            logs("New Developer (leave blank to keep): " + dev);
            if (!dev.isEmpty()) {
                gameList.set(index + 3, dev);
            }

            System.out.print("New Platform (leave blank to keep): ");
            String plat = sc.nextLine();
            logs("New Platform (leave blank to keep): " + plat);
            if (!plat.isEmpty()) {
                gameList.set(index + 4, plat);
            }

            save();
            System.out.println("Game updated!");
        } else {
            System.out.println("Invalid ID.");
        }
    }

    public void deleteGame() {
        displayList();
        System.out.print("\nEnter ID of the game to DELETE: ");
        int id = sc.nextInt();
        sc.nextLine();

        int index = (id - 1) * 5;
        if (index >= 0 && index < gameList.size()) {
            for (int i = 0; i < 5; i++) {
                gameList.remove(index);
            }
            save();
            System.out.println("Game deleted successfully.");
            logs("Game deleted successfully.");
        } else {
            System.out.println("Invalid ID.");
            logs("Invalid ID.");
        }
    }

    public void sortGames() {
        // Since it's a flat list, we need to group them into objects to sort easily
        List<String[]> temp = new ArrayList<>();
        for (int i = 0; i < gameList.size(); i += 5) {
            temp.add(new String[]{gameList.get(i), gameList.get(i + 1), gameList.get(i + 2), gameList.get(i + 3), gameList.get(i + 4)});
        }

        // Sort by Game Name (index 0 of the string array)
        temp.sort(Comparator.comparing(a -> a[0].toLowerCase()));

        // Flatten back to gameList
        gameList.clear();
        for (String[] game : temp) {
            gameList.addAll(Arrays.asList(game));
        }
        save();
        System.out.println("List sorted alphabetically by name.");
        logs("List sorted alphabetically by name.");
>>>>>>> 6396d3c8d0010e5778d8f1e57cfc2adb0de359ae
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
        logs("Enter Game Name: " + name);
        System.out.print("Enter Game Genre: ");
        String genre = sc.nextLine();
        logs("Enter Game Genre: " + genre);
        System.out.print("Enter Game Year: ");
        String year = sc.nextLine();
        logs("Enter Game Year: " + year);
        System.out.print("Enter Game Developer: ");
        String dev = sc.nextLine();
        logs("Enter Game Developer: " + dev);
        System.out.print("Enter Game Platform: ");
        String plat = sc.nextLine();
        logs("Enter Game Platform: " + plat);

        gameList.add(insertIndex, name);
        gameList.add(insertIndex + 1, genre);
        gameList.add(insertIndex + 2, year);
        gameList.add(insertIndex + 3, dev);
        gameList.add(insertIndex + 4, plat);

        save();
        System.out.println("Game inserted successfully!");
        logs("Game inserted successfully!");
    }

    public void searchGame() {
        System.out.print("\nEnter Game Name to search: ");
        String keyword = sc.nextLine().toLowerCase();
        logs("\nEnter Game Name to search: " + keyword);
        boolean found = false;

        System.out.println("\n--- SEARCH RESULTS ---");
        logs("\n--- SEARCH RESULTS ---");
        for (int i = 0; i < gameList.size(); i += 5) {
            if (gameList.get(i).toLowerCase().contains(keyword)) {
                printHeader();
                printRow((i / 5) + 1, i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching games found.");
            logs("No matching games found.");
        }
    }

    public void editGame() {
        displayList();
        System.out.print("\nEnter ID of the game to EDIT: ");
        int id = sc.nextInt();
        logs("\nEnter ID of the game to EDIT: " + id);
        sc.nextLine();

        int index = (id - 1) * 5;
        if (index >= 0 && index < gameList.size()) {
            System.out.print("New Name (leave blank to keep): ");
            String name = sc.nextLine();
            logs("New Name (leave blank to keep): " + name);
            if (!name.isEmpty()) {
                gameList.set(index, name);
            }

            System.out.print("New Genre (leave blank to keep): ");
            String genre = sc.nextLine();
            logs("New Genre (leave blank to keep): " + genre);
            if (!genre.isEmpty()) {
                gameList.set(index + 1, genre);
            }

            System.out.print("New Year (leave blank to keep): ");
            String year = sc.nextLine();
            logs("New Year (leave blank to keep): " + year);
            if (!year.isEmpty()) {
                gameList.set(index + 2, year);
            }

            System.out.print("New Developer (leave blank to keep): ");
            String dev = sc.nextLine();
            logs("New Developer (leave blank to keep): " + dev);
            if (!dev.isEmpty()) {
                gameList.set(index + 3, dev);
            }

            System.out.print("New Platform (leave blank to keep): ");
            String plat = sc.nextLine();
            logs("New Platform (leave blank to keep): " + plat);
            if (!plat.isEmpty()) {
                gameList.set(index + 4, plat);
            }

            save();
            System.out.println("Game updated!");
        } else {
            System.out.println("Invalid ID.");
        }
    }

    public void deleteGame() {
        displayList();
        System.out.print("\nEnter ID of the game to DELETE: ");
        int id = sc.nextInt();
        sc.nextLine();

        int index = (id - 1) * 5;
        if (index >= 0 && index < gameList.size()) {
            for (int i = 0; i < 5; i++) {
                gameList.remove(index);
            }
            save();
            System.out.println("Game deleted successfully.");
            logs("Game deleted successfully.");
        } else {
            System.out.println("Invalid ID.");
            logs("Invalid ID.");
        }
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
        logs("PLEASE ENTER A NUMBER.");
        sc.next();
        return;
    }
    
    int choice = sc.nextInt();
    logs("Sort Choice: " + choice);
    sc.nextLine();
    // Group into objects for sorting
    List<String[]> temp = new ArrayList<>();
    for (int i = 0; i < gameList.size(); i += 5) {
        temp.add(new String[]{
            gameList.get(i),     // Name
            gameList.get(i + 1), // Genre
            gameList.get(i + 2), // Year
            gameList.get(i + 3), // Dev
            gameList.get(i + 4)  // Platform
        });
    }
    switch (choice) {
        case 1 -> {
            temp.sort(Comparator.comparing(a -> a[0].toLowerCase()));
            System.out.println("List sorted by name (A-Z).");
            logs("List sorted by name (A-Z).");
        }
        case 2 -> {
            temp.sort((a, b) -> b[0].toLowerCase().compareTo(a[0].toLowerCase()));
            System.out.println("List sorted by name (Z-A).");
            logs("List sorted by name (Z-A).");
        }
        case 3 -> {
            temp.sort(Comparator.comparing(a -> a[2]));
            System.out.println("List sorted by year (Ascending).");
            logs("List sorted by year (Ascending).");
        }
        case 4 -> {
            temp.sort((a, b) -> b[2].compareTo(a[2]));
            System.out.println("List sorted by year (Descending).");
            logs("List sorted by year (Descending).");
        }
        default -> {
            System.out.println("Invalid choice. No sorting applied.");
            logs("Invalid choice. No sorting applied.");
            return;
        }
    }
    // Flatten back to gameList
    gameList.clear();
    for (String[] game : temp) {
        gameList.addAll(Arrays.asList(game));
    }
    save();
}

    public static void read() {
        gameList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("gameList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    gameList.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Starting with a fresh list (no file found).");
            logs("Starting with a fresh list (no file found).");
<<<<<<< HEAD
=======
        }
    }

    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("gameList.txt"))) {
            for (String item : gameList) {
                bw.write(item);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            logs("Error saving: " + e.getMessage());
        }
    }

    public void displayList() {
        if (gameList.isEmpty()) {
            System.out.println("\n[!] THE LIST IS CURRENTLY EMPTY.");
            logs("\n[!] THE LIST IS CURRENTLY EMPTY.");
            return;
        }
        printHeader();
        int count = 1;
        for (int i = 0; i < gameList.size(); i += 5) {
            printRow(count++, i);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        logs("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printHeader() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV", "PLATFORM");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        logs("-----------------------------------------------------------------------------------------------------------------------");
        logs(String.format("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV", "PLATFORM"));
        logs("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printRow(int id, int startIndex) {
        System.out.printf("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, gameList.get(startIndex), gameList.get(startIndex + 1),
                gameList.get(startIndex + 2), gameList.get(startIndex + 3), gameList.get(startIndex + 4));

        logs(String.format("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, gameList.get(startIndex), gameList.get(startIndex + 1),
                gameList.get(startIndex + 2), gameList.get(startIndex + 3), gameList.get(startIndex + 4)));
    }

    public static void logs(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
>>>>>>> 6396d3c8d0010e5778d8f1e57cfc2adb0de359ae
        }
    }

    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("gameList.txt"))) {
            for (String item : gameList) {
                bw.write(item);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            logs("Error saving: " + e.getMessage());
        }
    }

    public void displayList() {
        if (gameList.isEmpty()) {
            System.out.println("\n[!] THE LIST IS CURRENTLY EMPTY.");
            logs("\n[!] THE LIST IS CURRENTLY EMPTY.");
            return;
        }
        printHeader();
        int count = 1;
        for (int i = 0; i < gameList.size(); i += 5) {
            printRow(count++, i);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        logs("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printHeader() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV", "PLATFORM");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        logs("-----------------------------------------------------------------------------------------------------------------------");
        logs(String.format("%-4s | %-25s | %-25s | %-6s | %-20s | %-15s %n", "ID", "NAME", "GENRE", "YEAR", "DEV", "PLATFORM"));
        logs("-----------------------------------------------------------------------------------------------------------------------");
    }

    private void printRow(int id, int startIndex) {
        System.out.printf("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, gameList.get(startIndex), gameList.get(startIndex + 1),
                gameList.get(startIndex + 2), gameList.get(startIndex + 3), gameList.get(startIndex + 4));

        logs(String.format("%-4d | %-25.25s | %-25.25s | %-6.6s | %-20.20s | %-15.15s %n",
                id, gameList.get(startIndex), gameList.get(startIndex + 1),
                gameList.get(startIndex + 2), gameList.get(startIndex + 3), gameList.get(startIndex + 4)));
    }

    public static void logs(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}