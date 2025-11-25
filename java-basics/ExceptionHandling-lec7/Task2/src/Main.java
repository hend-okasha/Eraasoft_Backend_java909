import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("please enter the number: ");
            String num = input.nextLine();

            int number = Integer.parseInt(num);
            System.out.println("converted successfully");
            System.out.println("Number " + number);

        }catch (NumberFormatException exception){
            System.out.println("invalid value ");
        }

    }
}