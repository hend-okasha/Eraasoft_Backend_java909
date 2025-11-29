import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);


        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        //Flatten a list of lists into a single list.
        List<String> flatWords = nestedWords.stream()
                .flatMap(List::stream)
                .collect(toList());
        System.out.println("Flattened words: " + flatWords);

        //Extract all unique characters from a list of words.
        List<Character> uniqueChars = names.stream()
                .filter(Objects::nonNull)
                .flatMap(name -> name.chars().mapToObj(c -> (char) c))
                .distinct()
                .collect(toList());
        System.out.println("Unique characters: " + uniqueChars);

        //Filter a list of Optionals and collect non-empty values.
        List<Optional<String>> optionalNames = names.stream()
                .map(Optional::ofNullable)
                .collect(toList());

        List<String> nonEmpty = optionalNames.stream()
                .flatMap(Optional::stream)
                .collect(toList());
        System.out.println("Non-empty values: " + nonEmpty);

        //Map a list of strings to their lengths.
        List<String> nameWithLengths = names.stream()
                .filter(Objects::nonNull)
                .map(name -> name + " : " + name.length())
                .collect(Collectors.toList());

        System.out.println(nameWithLengths);

        //Return a list of uppercased words that start with “A”.
        List<String> namesStartWithA = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("uppercased names started with A: " + namesStartWithA);
    }
}