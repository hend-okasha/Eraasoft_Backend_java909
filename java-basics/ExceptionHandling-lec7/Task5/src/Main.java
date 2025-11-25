import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("file.txt");
            Scanner scanner= new Scanner(reader);

            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }catch (FileNotFoundException exception){
            System.out.println(" file not found ");
        }

    }
}