package MIDTERMS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PAGSM {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PAGGameStop GS = new PAGGameStop();
        date();
        System.out.println("Welcome to PAG Enterprise Systems\nWe've got it all for you!");
        Logger.pagsm("System started.");

        while (true) {
            System.out.println();
            System.out.println("""
                    Please choose one of the following:
                    1. PAG Grocery ShopperMart POS
                    2. PAG Movie Rental Registration
                    3. PAG GAMESTOP
                    4. PAG Quizist
                    5. Exit
                    """);
            System.out.print("Choice: ");
            int userinput = sc.nextInt();
            sc.nextLine();

            switch (userinput) {
                case 1 -> {
                    Logger.pagsm("Opened: PAG Grocery ShopperMart POS");
                    int iProdId;
                    String strProdName, strProdDesc;
                    double dQty, dPrice, dDiscount;
                    System.out.println("Loading PAG Grocery ShopperMart POS");
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
                    Logger.pagsm(String.format(
                            "POS transaction — ID:%d Name:\"%s\" Qty:%.2f Price:%.2f Discount:%.2f Subtotal:%.2f",
                            iProdId, strProdName, dQty, dPrice, dDiscount, dSubTotal));
                    System.out.println("Thank you for shopping with us.\n");
                    Logger.pagsm("POS session ended.");
                }
                case 2 -> {
                    Logger.pagsm("Opened: PAG Movie Rental Registration");
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
                        int choice = sc.nextInt(); sc.nextLine();
                        switch (choice) {
                            case 1 -> dvdtotal++;
                            case 2 -> vcdtotal++;
                            case 3 -> tapetotal++;
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
                        int choice1 = sc.nextInt(); sc.nextLine();
                        String category = switch (choice1) {
                            case 1 -> { horror++;   yield "Horror"; }
                            case 2 -> { scifi++;    yield "Scifi"; }
                            case 3 -> { drama++;    yield "Drama"; }
                            case 4 -> { comedy++;   yield "Comedy"; }
                            case 5 -> { cartoons++; yield "Cartoons"; }
                            default -> "Unknown";
                        };

                        System.out.print("Minutes: ");
                        int min = sc.nextInt(); sc.nextLine();

                        System.out.print("Setting: ");
                        String setting = sc.nextLine();

                        System.out.print("""
                                         1. Rental
                                         2. Sales
                                         Transaction: """);
                        int choice2 = sc.nextInt(); sc.nextLine();
                        String transType = switch (choice2) {
                            case 1 -> { rent++;  yield "Rental"; }
                            case 2 -> { sales++; yield "Sales"; }
                            default -> "Unknown";
                        };

                        System.out.print("Input Price: ");
                        int price = sc.nextInt(); sc.nextLine();

                        Logger.pagsm(String.format(
                                "Movie registered — Title:\"%s\" Category:%s Format:%s Transaction:%s Minutes:%d Setting:\"%s\" Price:%d",
                                title, category,
                                (choice == 1 ? "DVD" : choice == 2 ? "VCD" : "Tape"),
                                transType, min, setting, price));

                        System.out.print("Register another Y/N? ");
                        char ans = sc.nextLine().charAt(0);

                        if (ans != 'Y' && ans != 'y') {
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
                            Logger.pagsm(String.format(
                                    "Movie Rental session report — Rent:%d Sale:%d DVD:%d VCD:%d Tape:%d Horror:%d Scifi:%d Drama:%d Comedy:%d Cartoons:%d",
                                    rent, sales, dvdtotal, vcdtotal, tapetotal,
                                    horror, scifi, drama, comedy, cartoons));
                            System.out.println("Thank you for shopping with us.\n");
                            run = false;
                        }
                    }
                    Logger.pagsm("Movie Rental session ended.");
                }
                case 3 -> {
                    Logger.pagsm("Opened: PAG GameStop");
                    GS.run();
                    Logger.pagsm("Returned from PAG GameStop.");
                }
                case 4 -> {
                    Logger.pagsm("Opened: PAG Quizist");
                    Quizist.run();
                    Logger.pagsm("Returned from PAG Quizist.");
                }
                case 5 -> {
                    System.out.println("Thank you for using PAG Enterprise Systems\nGood bye");
                    Logger.pagsm("System exited by user.");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice. Please enter 1-5.");
                    Logger.pagsm("Invalid menu choice entered: " + userinput);
                }
            }
        }
    }

    public static String InputString() { return sc.nextLine(); }

    public static int InputInt() {
        int temp = sc.nextInt();
        sc.nextLine();
        return temp;
    }

    public static double InputDouble() { return sc.nextDouble(); }

    public static double computeSalary(double dQty, double dPrice, double dDiscount) {
        return (dPrice * dQty) - dDiscount;
    }

    public static void displayProduct(int iProdId, String strProdName, String strProdDesc,
            double dQty, double dPrice, double dDiscount, double dSubTotal) {
        System.out.println(iProdId + " " + strProdName
                + "\nPriced at " + dPrice + " for " + dQty + " pieces"
                + "\nDiscounted at " + dDiscount + "\nSubtotal: " + dSubTotal);
    }

    public static void date() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a")
                                                 .withLocale(Locale.forLanguageTag("en-PH"));
        System.out.println("Current Date and Time : " + now.format(fmt));
    }
}
