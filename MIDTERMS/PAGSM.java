package MIDTERMS;

import java.util.*;

public class PAGSM {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PAGGameStop GS = new PAGGameStop();
        System.out.println("Welcome to PAG Enterprise Systems\nWe've got it all for you!");

        while (true) {
            System.out.println();
            System.out.println("""
                    Please choose one of the following:
                    1. PAG Grocery ShopperMart POS
                    2. PAG Movie Rental Registration
                    3. PAG GAMESTOP
                    4. Exit
                    """);
            System.out.print("Choice: ");
            int userinput = sc.nextInt();
            sc.nextLine();
            switch (userinput) {
                case 1 -> {
                    // PAG Grocery ShopperMart POS
                    System.out.println("Loading PAG Grocery ShopperMart POS");
                    int iProdId;
                    String strProdName,
                            strProdDesc;
                    double dQty,
                            dPrice,
                            dDiscount;
                    System.out.println("Please input the following:");
                    System.out.print("ProductID: ");
                    iProdId = InputInt();
                    System.out.print("Name: ");
                    strProdName = InputString();
                    System.out.print("Description: ");
                    strProdDesc = InputString();
                    System.out.print("Quantity: ");
                    dQty = InputDouble();
                    System.out.print("Price: ");
                    dPrice = InputDouble();
                    System.out.print("Discount: ");
                    dDiscount = InputDouble();
                    double dSubTotal = computeSalary(dQty, dPrice, dDiscount);
                    displayProduct(iProdId, strProdName, strProdDesc, dQty, dPrice, dDiscount, dSubTotal);
                    System.out.println("Thank you for shopping with us.\n");
                }
                case 2 -> {
                    // PAG Movie Rental Registration
                    System.out.println("Loading PAG Movie Rental Registration");
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

                        System.out.print("""
                                                                 1. Horror
                                                                 2. Scifi
                                                                 3. Drama
                                                                 4. Comedy
                                                                 5. Cartoons
                                                                 Category: """);

                        int choice1 = sc.nextInt();
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
                        sc.nextLine();

                        System.out.print("Setting: ");
                        String setting = sc.nextLine();

                        System.out.print("""
                                                                 1. Rental
                                                                 2. Sales
                                                                 Transaction: """);

                        int choice2 = sc.nextInt();
                        sc.nextLine();

                        switch (choice2) {
                            case 1 ->
                                rent++;
                            case 2 ->
                                sales++;
                        }

                        System.out.print("Input Price: ");
                        int price = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Register another Y/N? ");
                        char ans = sc.nextLine().charAt(0);

                        if (ans == 'Y' || ans == 'y') {
                        } else {
                            System.out.println("\nREPORTS");
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
                            System.out.println("Thank you for shopping with us.\n");
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
    }
}
