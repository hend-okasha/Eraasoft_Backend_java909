import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        //Calculate the sum of a list of integers using reduce
        int sumOfIntegers = numbers.stream().reduce(0,Integer::sum);
        System.out.println(" sum of integer list = " + sumOfIntegers);

        //find max number and min number in list
        int maxNumber = numbers.stream().max(Integer::compare).get();
        System.out.println(" max value in this list = " + maxNumber );

        int minNumber = numbers.stream().min(Integer::compare).get();
        System.out.println(" min number in this list = " + minNumber);

        //Calculate the average of a list of doubles
        double average = numbers.stream().mapToDouble(num -> num)
                .average().getAsDouble();
        System.out.println(" average of double list = " + average);

        //Multiply all integers in a list together using reduce
        numbers= Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 4);
        int product = numbers.stream().reduce(1, (a,b) -> a*b);
        System.out.println(" product of list = " + product);

        //Count how many numbers are positive in a list
         numbers= Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Long count = numbers.stream().filter(num -> num > 0).count();
        System.out.println(" number of positive elements in list = " + count);


    }
}