import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String text = null;

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("program to divide two numbers");
            System.out.println("enter number");
            int number = input.nextInt();

            System.out.println("enter divisor");
            int divisor = input.nextInt();

            int result = number / divisor;
            System.out.println(result);

            System.out.println(text.length());
        }catch (NullPointerException exception){
            System.out.println(" can't use null text");
        }catch (ArithmeticException exception){
            System.out.println(" can't divide by zero");
        }catch (InputMismatchException exception){
            System.out.println(" invalid value");
        }catch (Exception exception){
            System.out.println("something wrong happened ");
        }

    }
}