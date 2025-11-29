import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        //Filter even numbers from a list of integers.
        numbers = numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(numbers);

        //Sort a list of integers in descending order using streams.
        numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        numbers = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(numbers);

        //Remove duplicate elements from a list using distinct()
        numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        numbers = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println(numbers);

        //Find names starting with a specific letter from a list of strings.
        names = names.stream()
                .filter(name -> name != null)
                .filter(name -> !name.isEmpty())
                .filter(name -> name.charAt(0) == 'A').collect(Collectors.toList());
        System.out.println(names);

        //Convert all strings to uppercase using stream.
        names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        names = names.stream()
                .filter(name -> name != null)
                .filter(name -> !name.isEmpty())
                .map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println(names);

    }
}