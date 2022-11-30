import java.util.Scanner;

public class LinearSystem {
    private int m, n;
    private float[][] matrix;

    public void createSystem(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Input m: ");
        m = scan.nextInt();
        System.out.println("Input n: ");
        n = scan.nextInt();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                matrix[m][n] = scan.nextFloat();
            }
        }
    }

    public void displaySystem() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(matrix[m][n] + " ");
            }
            System.out.println();
        }
    }

}
