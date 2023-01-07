import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class LinearSystem {
    private int m, n, type = 0;
    private double koef = 0.0d;
    private double[][] matrix;
    private double[][] tempMatrix;

    public void create(){
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Input m: ");
        m = scan.nextInt();
        System.out.print("Input n: ");
        n = scan.nextInt();

        matrix = new double[m][n+1];
        tempMatrix = new double[m][n+1];
        System.out.println("\nInput system " + m + "x" + n + " with result col:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] = scan.nextDouble();
            }
        }
        System.out.println();
    }

    public void display() {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void result(){
        switch (type) {
            case 0 -> System.out.println("The system is not initialized/solved!");
            case 1 -> {
                System.out.println("A single solution exists for this linear system:");
                DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                for (int i = 0; i < m; i++) {
                    System.out.println("x" + i + " = " + df.format(matrix[i][n]).replaceAll("^-(?=0(\\.0*)?$)", ""));
                }
            }
            case 2 -> System.out.println("There are no solutions for this linear system.");
            case 3 -> {
                System.out.println("There is an infinite number of solutions for this linear system:");
                for (int i = 0; i < m; i++) {
                    System.out.print("x" + i +" = ");
                    if (matrix[i][i] != 0) {
                        for (int j = i + 1; j < n; j++) {
                            if (matrix[i][j] != 0) {
                                System.out.print(-matrix[i][j] + "*x" + j + " ");
                            }
                        }
                        System.out.println(matrix[i][n] > 0 ? "+ " + matrix[i][n] : matrix[i][n]);
                    } else {
                        System.out.println("R");
                    }

                }
            }
            default -> System.out.println("Error in code.");
        }

        System.out.println();
    }
    public void solve(){
        type = 1;

        for (int i = 0; i < m-1; i++) {
            if (matrix[i][i] == 0 && i<m-1) {

                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < n + 1; k++) {
                        tempMatrix[j][k] = matrix[j][k];
                    }
                }


                for (int k = 0; k < n + 1; k++) {
                    for (int j = i; j < m - 1; j++) {

                        tempMatrix[j][k] = matrix[j + 1][k];
                    }
                    tempMatrix[m - 1][k] = matrix[i][k];
                }

                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < n + 1; k++) {
                        matrix[j][k] = tempMatrix[j][k];
                    }
                }
            }
            for (int j = i + 1; j < m; j++) {
                if (matrix[i][i] != 0) koef = matrix[j][i] / matrix[i][i];
                for (int k = i; k < n+1; k++) {
                    matrix[j][k] = matrix[j][k] - matrix[i][k] * koef;
                }
            }

            display();

        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][i] != 1 && matrix[i][i] != 0) {
                for (int j = n; j >= i; j--) {
                    matrix[i][j] /= matrix[i][i];
                }
            }
        }

        for (int i = m - 1; i > 0; i--) {
            if (matrix[i][i] != 0) {
                for (int j = i-1; j >= 0; j--) {
                    if (matrix[i][i] != 0) koef = matrix[j][i] / matrix[i][i];
                    for (int k = 0; k < n + 1; k++) {
                        matrix[j][k] = matrix[j][k] - matrix[i][k] * koef;
                    }
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            if (matrix[i][i] == 0 && matrix[i][n] == 0) {
                type = 3;
            }
            if (matrix[i][i] == 0 && matrix[i][n] != 0) {
                type = 2;
            }
        }
        if (type != 2 && type != 3) {
            type = 1;
        }

        display();

    }

}
