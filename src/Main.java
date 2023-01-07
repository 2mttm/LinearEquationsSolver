import java.util.Locale;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        LinearSystem sys = new LinearSystem();

        sys.create();
        sys.solve();
        sys.result();
    }
}