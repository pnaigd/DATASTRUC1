
import java.util.Scanner;

public class LabActivity {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int iProdId;
        String strProdName, strProdDesc;
        double dQty, dPrice, dDiscount;

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
