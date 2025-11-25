import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            readFile();
        }catch (IOException exception){
            System.out.println(" file not found ");
        }
    }

    public static void readFile() throws IOException {
        FileReader reader = new FileReader("file.txt");
        Scanner scanner = new Scanner(reader);

        while( scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }
}