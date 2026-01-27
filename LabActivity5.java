
import java.util.Scanner;

public class LabActivity5 {

    public static Scanner sc = new Scanner(System.in);
    public static String[] strGames = new String[10];

    public static void main(String[] args) {
        int counter = 0;
        System.out.println("Welcome to GaMeStOp!");
        boolean register = true;
        do {
            System.out.print("Enter Game Name: ");
            String strGameName = assigner(InputString());

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
        print();
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

    public static String assigner(String strGameName) {
        String GameName = "";
        for (int i = 0; i < strGames.length; i++) {
            strGames[i] = sc.nextLine();
        }
        return GameName;
    }

    public static void print() {
        for (int i = 0; i < strGames.length; i++) {
            System.out.println(strGames[i]);
        }
    }
}
