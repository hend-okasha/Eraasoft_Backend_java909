
public class Main {
    public static void main(String[] args) {
       String str = null;
       String message = "Hello world ";
       convertToUpperCase(str);
       convertToUpperCase(message);
    }
    public static void convertToUpperCase (String text){
        try {
            String upper = text.toUpperCase();
            System.out.println("converted to upper case : " + upper );
        }catch (NullPointerException exception){
            System.out.println(" string is null can not converted to upper case");
        }

    }
}