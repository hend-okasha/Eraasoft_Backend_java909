import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("please enter first number: ");
            double num1 = input.nextInt();

            System.out.println("please enter second number: ");
            double num2 = input.nextInt();

            double result = num1/num2;
            System.out.println("num1 / num2 = " + result);

        }catch (ArithmeticException exception){
            System.out.println("can not divide by zero");
        }catch (InputMismatchException exception){
            System.out.println("invalid value");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
}