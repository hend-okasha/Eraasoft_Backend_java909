import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        //Count the number of strings longer than 5 characters.
        Long strLonger5 = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 5).count();
        System.out.println("the number of strings longer than 5 characters = " + strLonger5);

        //Find the first element in a stream that matches a given condition.
        String firstNameStartingWithS = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.charAt(0) == 'S')
                .findFirst()
                .orElse("no strings start with s letter");
        System.out.println("first string start with s letter is " + firstNameStartingWithS);

        //any number is divisible by 5 in a list
        numbers = numbers.stream()
                .filter(num -> num % 5 == 0)
                .collect(Collectors.toList());
        System.out.println(" numbers divisible by 5 are : " + numbers);

        // collect list in a set
        Set<String> stringSet = names.stream().collect(Collectors.toSet());
        System.out.println("set of strings : " + stringSet);

        //skip first 3 elements
        names = names.stream().skip(3).collect(Collectors.toList());
        System.out.println("list after skip first 3 element : "+ names);
    }
}