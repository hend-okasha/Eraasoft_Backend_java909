import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println(" enter your age: ");
            int age = input.nextInt();
            checkAge(age);

            System.out.println("Access granted. valid age");
        }catch (InvalidAgeException exception){
            System.out.println(exception.getMessage());
        }catch (InputMismatchException exception){
            System.out.println("invalid value");
        } catch (Exception e) {
            System.out.println("something wrong happened");
        }

    }

    public static void checkAge(int age) throws InvalidAgeException{
        if( age <= 18 ){
            throw new InvalidAgeException("Age must be 18 or above ");
        }
    }
}