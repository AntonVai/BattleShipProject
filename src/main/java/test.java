import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toUpperCase();
        int startRow = Integer.parseInt(input.substring(1));
        System.out.println(input.charAt(0));
        System.out.println(startRow);
    }
}
