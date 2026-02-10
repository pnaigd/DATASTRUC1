
import java.util.Scanner;

public class hobbies {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String strHobbies;
        int iAllowance;
        double dAverage;

        iAllowance = 200;

        System.out.println("“What are the things that you like to.");
        strHobbies = sc.nextLine();

        System.out.println("How old are you?");
        int iAge = sc.nextInt();

        System.out.println("My Allowance is: " + iAllowance);

        System.out.println(strHobbies + "are the things that I like to do");

        System.out.print("Input Average: ");
        dAverage = sc.nextDouble();
        System.out.println("Average is <" + dAverage + ">");
    }
}
