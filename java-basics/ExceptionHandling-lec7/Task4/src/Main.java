import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int arr[] = {10 ,20 ,30, 40, 50};
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("enter index of array you want to print (0-4): ");
            int index = input.nextInt();
            int result = arr[index];
            System.out.println(" the number in this index is: " + result);
        }catch (ArrayIndexOutOfBoundsException exception){
            System.out.println(" the index not available in this array");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
}