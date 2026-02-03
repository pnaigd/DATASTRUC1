
import java.util.Scanner;

public class LabActivity7 {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to EFM Enterprise Systems\nWe've got it all for you!");

        while (true) {
            System.out.println();
            System.out.println("""
                    Please choose one of the following:
                    1. EFM Grocery ShopperMart POS 
                    2. EFM Movie Rental Registration
                    3. EFM Colleges Enrollment Registration
                    4. Exit
                    """);
            System.out.print("Choice: ");
            int userinput = sc.nextInt();
            switch (userinput) {
                case 1://EFM Grocery ShopperMart POS 
                    System.out.println("Loading EFM Grocery ShopperMart POS");
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
                    break;
                case 2://EFM Movie Rental Registration
                    System.out.println("Loading EFM Movie Rental Registration");
                    int rent = 0,
                     sales = 0;
                    int comedy = 0,
                     horror = 0,
                     scifi = 0,
                     drama = 0,
                     cartoons = 0;
                    int dvdtotal = 0,
                     vcdtotal = 0,
                     tapetotal = 0;

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
                            case 1:
                                dvdtotal++;
                                break;
                            case 2:
                                vcdtotal++;
                                break;
                            case 3:
                                tapetotal++;
                                break;
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
                            case 1:
                                horror++;
                                break;
                            case 2:
                                scifi++;
                                break;
                            case 3:
                                drama++;
                                break;
                            case 4:
                                comedy++;
                                break;
                            case 5:
                                cartoons++;
                                break;
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
                            case 1:
                                rent++;
                                break;
                            case 2:
                                sales++;
                                break;
                        }

                        System.out.print("Input Price: ");
                        int price = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Register another Y/N? ");
                        char ans = sc.nextLine().charAt(0);

                        if (ans == 'Y' || ans == 'y') {
                            continue;
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
                    break;
                case 3:
                    System.out.println("Loading GaMeStOp");
                    int counter = 0;
                    System.out.println("Welcome to GaMeStOp!");
                    boolean register = true;
                    do {
                        System.out.print("Enter Game Name: ");
                        String strGameName = InputString();

                        System.out.print("Enter Game Genre: ");
                        String strGameGenre = InputString();
                        counter++;

                        System.out.print("Do you want to register another game? Y/N : ");
                        String strChoice = InputString();
                        char cChoice = strChoice.charAt(0);
                        System.out.println("GAMES[" + counter + "/10]");
                        if (cChoice == 'n' || cChoice == 'N') {
                            register = false;
                        }
                        if (counter == 10) {
                            System.out.println("Maximum games reached.");
                            register = false;
                        }
                    } while (register);
                    System.out.println("Number of Games regstered: [" + counter + "/10]");
                    System.out.println("Thank you for shopping with us.\n");
                    break;
                case 4://bye
                    System.out.println("Thank you for using EFM Enterprise Systems\nGood bye");
                    System.exit(0);
                    break;
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

    public static void displayProduct(int iProdId, String strProdName, String strProdDesc, double dQty, double dPrice, double dDiscount, double dSubTotal) {
        System.out.println(iProdId + " " + strProdName + "\nPriced at " + dPrice + " for " + dQty + " pieces" + "\nDiscounted at " + dDiscount + "\nSubtotal: " + dSubTotal);
    }
}
