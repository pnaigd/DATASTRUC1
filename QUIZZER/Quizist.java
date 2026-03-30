package QUIZZER;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Quizist {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.print("""
                MENU
                [1] REGISTRATION
                [2] PLAY
                [3] EXIT
                CHOICE: """);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("""
                    PLAYER REGISTRATION
                    [1] LOGIN
                    [2] NEW PLAYER
                    [3] BACK
                    CHOICE: """);
                    int user = sc.nextInt();
                    switch (user) {
                        case 1 ->
                            System.out.println("LOGIN");
                        case 2 ->
                            System.out.println("NEW PLAYER");
                        case 3 ->
                            System.out.println("BACK TO MAIN MENU");
                        default ->
                            throw new InputMismatchException();
                    }
                    break;

                case 2:
                    System.out.println("play game");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    throw new InputMismatchException();
            }
            sc.close();
        }
    }
}
