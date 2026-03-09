package MIDTERMS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PAGGameStop {

    public static List<String> gameList = new ArrayList<>();
    public static boolean register = true;
    public static Scanner sc = new Scanner(System.in);

    public void run() {
        read();
        //menu
        System.out.println("""
                MENU
                [1] ADD 
                [2] SEARCH 
                [3] EDIT
                [4] DELETE  
                [5] SORT
                [6] LIST
                [0] EXIT
                """);
        System.out.print("Choice: ");
        int user = sc.nextInt();
        switch (user) {
            case 1:
                System.out.println("add");
            case 2:
                System.out.println("search");
            case 3:
                System.out.println("edit");
            case 4:
                System.out.println("delete");
            case 5:
                System.out.println("sort");
            case 6:
                System.out.println("list");
                int count = 0;
                int counter = 2;
                if (!gameList.isEmpty()) {
                    System.out.print("1. ");
                    for (String games : gameList) {
                        System.out.print(games + "|");
                        count++;
                        if (count == 5) {
                            System.out.println();
                            System.out.print(counter + ". ");
                            count = 0;
                            counter++;
                        }
                    }
                } else {
                    System.out.println("LIST IS EMPTY");
                }
                break;
            case 0:
                System.out.println("exit");
                System.exit(0);
        }

        //registration
        // do {
        //     System.out.print("Enter Game Name: ");
        //     String strGameName = sc.nextLine();
        //     System.out.print("Enter Game Genre: ");
        //     String strGameGenre = sc.nextLine();
        //     System.out.print("Enter Game Year: ");
        //     String strGameYear = sc.nextLine();
        //     System.out.print("Enter Game Developer: ");
        //     String strDev = sc.nextLine();
        //     System.out.print("Enter Game Platform: ");
        //     String strPlatform = sc.nextLine();
        //     //prompt for another registration
        //     System.out.print("Do you want to register another game? Y/N : ");
        //     String strChoice = sc.nextLine();
        //     char cChoice = strChoice.charAt(0);
        //     if (cChoice == 'n' || cChoice == 'N') {
        //         register = false;
        //     }
        // } while (register);
        //reader
    }

    public static void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("gameList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                gameList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("Loaded " + (gameList.size() - 1) + " games.");
    }
}
