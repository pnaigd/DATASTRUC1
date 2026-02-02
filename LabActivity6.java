
import java.io.FileWriter;
import java.util.Scanner;

public class LabActivity6 {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        FileWriter fw = new FileWriter("act6.txt");

        int counter = 0;
        System.out.println("Welcome to GaMeStOp!");
        fw.write("Welcome to GaMeStOp!\n");
        boolean register = true;
        do {
            counter++;
            fw.write("[-----------------------------------]");
            fw.write("\nGAMES[" + counter + "/10]\n");
            System.out.print("Enter Game Name: ");
            fw.write("Game Name: " + InputString() + "\n");

            System.out.print("Enter Game Genre: ");
            fw.write("Game Genre: " + InputString() + "\n");

            System.out.print("Enter Game Price: ");
            fw.write("Game Price: " + InputDouble() + "\n");
            sc.nextLine();
            System.out.print("Do you want to register another game? Y/N : ");
            String strChoice = InputString();
            char cChoice = strChoice.charAt(0);

            System.out.println("GAMES[" + counter + "/10]");
            if (cChoice == 'n' || cChoice == 'N') {
                register = false;
                fw.write("[-----------------------------------]");
                fw.flush();
            }
            if (counter == 10) {
                System.out.println("Maximum games reached.");
                register = false;
                fw.write("[-----------------------------------]");
                fw.flush();
            }
        } while (register);
        System.out.println("Number of Games registered: [" + counter + "/10]");
        fw.close();
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
}
