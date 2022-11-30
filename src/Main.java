//This is a sample program to solve the linear equations.
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        LinearSystem system = new LinearSystem();

        system.createSystem();
    }
}