//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Outer try starts");

            try {
                System.out.println("Inner try: dividing numbers");
                int result = 10 / 0;
                System.out.println("This line won't run");
            } catch (NullPointerException exception) {
                System.out.println("Caught NullPointerException in inner catch");
            }

            System.out.println("Outer try continues");

        } catch (ArithmeticException exception) {
            System.out.println("Caught ArithmeticException in outer catch: " + exception.getMessage());
        }
    }
}