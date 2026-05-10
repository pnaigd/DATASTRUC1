package MIDTERMS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PAGSM {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PAGGameStop GS = new PAGGameStop();
        date();
        System.out.println("Welcome to PAG Enterprise Systems\nWe've got it all for you!");
        logs("Welcome to PAG Enterprise Systems\nWe've got it all for you!");

        while (true) {
            System.out.println();
            System.out.println("""
                    Please choose one of the following:
                    1. PAG Grocery ShopperMart POS
                    2. PAG Movie Rental Registration
                    3. PAG GAMESTOP
                    4. Exit
                    """);
            logs("""
                    Please choose one of the following:
                    1. PAG Grocery ShopperMart POS
                    2. PAG Movie Rental Registration
                    3. PAG GAMESTOP
                    4. Exit
                    """);
            System.out.print("Choice: ");
            int userinput = sc.nextInt();
            logs("Choice: " + userinput);
            sc.nextLine();
            switch (userinput) {
                case 1 -> {
                    // PAG Grocery ShopperMart POS
                    System.out.println("Loading PAG Grocery ShopperMart POS");
                    logs("Loading PAG Grocery ShopperMart POS");
                    int iProdId;
                    String strProdName,
                            strProdDesc;
                    double dQty,
                            dPrice,
                            dDiscount;
                    System.out.println("Please input the following:");
                    logs("Please input the following:");
                    System.out.print("ProductID: ");
                    iProdId = InputInt();
                    logs("ProductID: " + iProdId);
                    System.out.print("Name: ");
                    strProdName = InputString();
                    logs("Name: " + strProdName);
                    System.out.print("Description: ");
                    strProdDesc = InputString();
                    logs("Description: " + strProdDesc);
                    System.out.print("Quantity: ");
                    dQty = InputDouble();
                    logs("Quantity: " + dQty);
                    System.out.print("Price: ");
                    dPrice = InputDouble();
                    logs("Price: " + dPrice);
                    System.out.print("Discount: ");
                    dDiscount = InputDouble();
                    logs("Discount: " + dDiscount);
                    double dSubTotal = computeSalary(dQty, dPrice, dDiscount);
                    displayProduct(iProdId, strProdName, strProdDesc, dQty, dPrice, dDiscount, dSubTotal);
                    System.out.println("Thank you for shopping with us.\n");
                    logs("Thank you for shopping with us.\n");
                }
                case 2 -> {
                    // PAG Movie Rental Registration
                    System.out.println("Loading PAG Movie Rental Registration");
                    logs("Loading PAG Movie Rental Registration");
                    int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0, drama = 0,
                            cartoons = 0, dvdtotal = 0, vcdtotal = 0, tapetotal = 0;
                    boolean run = true;
                    while (run) {
                        System.out.print("""
                                                                 Registration
                                                                 1. DVD
                                                                 2. VCD
                                                                 3. Tape
                                                                 Choice: """);

                        int choice = sc.nextInt();
                        logs("""
                                                                 Registration
                                                                 1. DVD
                                                                 2. VCD
                                                                 3. Tape
                                                                 Choice: """ + choice);
                        sc.nextLine();

                        switch (choice) {
                            case 1 ->
                                dvdtotal++;
                            case 2 ->
                                vcdtotal++;
                            case 3 ->
                                tapetotal++;
                        }

                        System.out.print("Input Title: ");
                        String title = sc.nextLine();
                        logs("Input Title: " + title);
                        System.out.print("""
                                                                 1. Horror
                                                                 2. Scifi
                                                                 3. Drama
                                                                 4. Comedy
                                                                 5. Cartoons
                                                                 Category: """);

                        int choice1 = sc.nextInt();
                        logs("""
                                                                 1. Horror
                                                                 2. Scifi
                                                                 3. Drama
                                                                 4. Comedy
                                                                 5. Cartoons
                                                                 Category: """ + choice1);
                        sc.nextLine();

                        switch (choice1) {
                            case 1 ->
                                horror++;
                            case 2 ->
                                scifi++;
                            case 3 ->
                                drama++;
                            case 4 ->
                                comedy++;
                            case 5 ->
                                cartoons++;
                        }

                        System.out.print("Minutes: ");
                        int min = sc.nextInt();
                        logs("Minutes: " + min);
                        sc.nextLine();

                        System.out.print("Setting: ");
                        String setting = sc.nextLine();
                        logs("Setting: " + setting);
                        System.out.print("""
                                                                 1. Rental
                                                                 2. Sales
                                                                 Transaction: """);

                        int choice2 = sc.nextInt();
                        logs("""
                                                                 1. Rental
                                                                 2. Sales
                                                                 Transaction: """ + choice2);
                        sc.nextLine();

                        switch (choice2) {
                            case 1 ->
                                rent++;
                            case 2 ->
                                sales++;
                        }

                        System.out.print("Input Price: ");
                        int price = sc.nextInt();
                        logs("Input Price: " + price);
                        sc.nextLine();

                        System.out.print("Register another Y/N? ");
                        char ans = sc.nextLine().charAt(0);
                        logs("Register another Y/N? " + ans);

                        if (ans == 'Y' || ans == 'y') {
                        } else {
                            System.out.println("\nREPORTS");
                            logs("\nREPORTS");
                            System.out.printf("""
                                                                          For rent: %d
                                                                          For Sale: %d
                                                                          VCD Total: %d
                                                                          DVD Total: %d
                                                                          Tape Total: %d
                                                                          Horror movies: %d
                                                                          Scifi movies: %d
                                                                          Drama movies: %d
                                                                          Comedy movies: %d
                                                                          Cartoons movies: %d
                                                                          """,
                                    rent, sales, vcdtotal, dvdtotal, tapetotal,
                                    horror, scifi, drama, comedy, cartoons);
                            logs(String.format("""
                                                                          For rent: %d
                                                                          For Sale: %d
                                                                          VCD Total: %d
                                                                          DVD Total: %d
                                                                          Tape Total: %d
                                                                          Horror movies: %d
                                                                          Scifi movies: %d
                                                                          Drama movies: %d
                                                                          Comedy movies: %d
                                                                          Cartoons movies: %d
                                                                          """,
                                    rent, sales, vcdtotal, dvdtotal, tapetotal,
                                    horror, scifi, drama, comedy, cartoons));
                            System.out.println("Thank you for shopping with us.\n");
                            logs("Thank you for shopping with us.\n");
                            run = false;
                        }
                    }
                }
                case 3 -> {
                    GS.run();
                }
                case 4 -> {
                    // bye
                    System.out.println("Thank you for using PAG Enterprise Systems\nGood bye");
                    logs("Thank you for using PAG Enterprise Systems\nGood bye");
                    System.exit(0);
                }
            }
        }
    }

    public static String InputString() {
        String temp = sc.nextLine();
        return temp;
    }

    public static int InputInt() {
        int temp = sc.nextInt();
        sc.nextLine();
        return temp;
    }

    public static double InputDouble() {
        double temp = sc.nextDouble();
        return temp;
    }

    public static double computeSalary(double dQty, double dPrice, double dDiscount) {
        double dSubTotal = (dPrice * dQty) - dDiscount;
        return dSubTotal;
    }

    public static void displayProduct(int iProdId, String strProdName, String strProdDesc, double dQty, double dPrice,
            double dDiscount, double dSubTotal) {
        System.out.println(iProdId + " " + strProdName + "\nPriced at " + dPrice + " for " + dQty + " pieces"
                + "\nDiscounted at " + dDiscount + "\nSubtotal: " + dSubTotal);
        logs(iProdId + " " + strProdName + "\nPriced at " + dPrice + " for " + dQty + " pieces"
                + "\nDiscounted at " + dDiscount + "\nSubtotal: " + dSubTotal);
    }

    public static void logs(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static void date() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a")
                                                       .withLocale(Locale.forLanguageTag("en-PH"));

        String humanReadableDateTime = currentDateTime.format(formatter);
        System.out.println("Current Date and Time : " + humanReadableDateTime);
        logs("Current Date and Time : " + humanReadableDateTime);
    }
}
