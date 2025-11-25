import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("program start");
        try {
            System.out.println("divide of two numbers");
            Scanner input = new Scanner(System.in);
            System.out.println("enter first number");
            int num1 = input.nextInt();

            System.out.println(" enter second number");
            int num2 = input.nextInt();

            int result = num1 / num2;
            System.out.println("result of division: " + result );
        }catch (ArithmeticException exception){
            System.out.println("can not divide by 0 ");
            return;
        }catch (InputMismatchException exception){
            System.out.println(" invalid value");
            return;
        }catch (Exception exception){
            System.out.println(" something went wrong");
            return;
        }finally {
            System.out.println("finally block execution");
        }

    }
}