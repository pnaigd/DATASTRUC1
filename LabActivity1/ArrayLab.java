
public class ArrayLab {

    public static void main(String[] args) {
        int[] alpha = new int[15];
        for (int i = 0; i < alpha.length; i++) {
            alpha[i] = i + 1;
        }
        System.out.println("Tenth element: " + alpha[9]);
        alpha[4] = 3;
        alpha[8] = alpha[5] + alpha[12];
        alpha[3] = (3 * alpha[7]) - 57;

        System.out.println("\nPrinting array (5 per line):");

        for (int i = 0; i < alpha.length; i++) {
            System.out.print(alpha[i] + "   ");

            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
}
