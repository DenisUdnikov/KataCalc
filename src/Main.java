import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        String output = calc(input);
        System.out.println(output);
    }

    public static String calc(String input) throws Exception {
        Expression expt = new Expression(input);
        return expt.getResult();
    }
}