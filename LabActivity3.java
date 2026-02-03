
import java.util.Scanner;

public class LabActivity3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rent = 0, sales = 0;
        int comedy = 0, horror = 0, scifi = 0, drama = 0, cartoons = 0;
        int dvdtotal = 0, vcdtotal = 0, tapetotal = 0;

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
                run = false;
            }
        }
    }
}

}
}
