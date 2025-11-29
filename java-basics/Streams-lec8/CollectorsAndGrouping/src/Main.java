import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        //Group students by department
        Map<String, List<Student>> groupByDept = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("students grouped by Department: " + groupByDept);

        //Partition a list of numbers into even and odd using partitioningBy.
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(num -> num %2 ==0));
        System.out.println("even numbers "+ partitioned.get(true));
        System.out.println("odd numbers " + partitioned.get(false));

        //Create a comma-separated string from a list of strings.
        String separatedNames = names.stream()
                .collect(Collectors.joining(" , "));
        System.out.println("separated names with comma :" + separatedNames);

        //Group employees by age and count how many per age.
        Map<Integer, Long> countByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge , Collectors.counting()));
        System.out.println("number of employees in each age "+countByAge);

        //Find the average salary per department in a list of employees.
        Map<String ,Double> avgSalaryEachDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment , Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("average salary in each department = " + avgSalaryEachDept);


    }
}