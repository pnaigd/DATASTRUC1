package MIDTERMS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PAGGameStop {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Loading GaMeStOp");
        int counter = 0;
        System.out.println("Welcome to GaMeStOp!");
        boolean register = true;
        do {
            System.out.print("Enter Game Name: ");
            String strGameName = sc.nextLine();
            System.out.print("Enter Game Genre: ");
            String strGameGenre = sc.nextLine();
            System.out.print("Enter Game Year: ");
            String strGameYear = sc.nextLine();
            System.out.print("Enter Game Developer: ");
            String strDev = sc.nextLine();
            System.out.print("Enter Game Platform: ");
            String strPlatform = sc.nextLine();
            counter++;

            System.out.print("Do you want to register another game? Y/N : ");
            String strChoice = sc.nextLine();
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
        System.out.println("Number of Games registered: [" + counter + "/10]");
        System.out.println("Thank you for shopping with us.\n");
        try (BufferedReader br = new BufferedReader(new FileReader("gameList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
