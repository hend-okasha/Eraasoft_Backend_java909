public class Main {
    public static void main(String[] args) {
        try {
            callingRiskFunction();
        }catch (Exception exception){
            System.out.println(" exception caught in main "+exception.getMessage());
        }
    }

    public static void riskMethod() throws Exception{
        System.out.println("risk method - throw exception");
        throw new Exception("something went wrong");
    }

    public static void callingRiskFunction() throws Exception{
        System.out.println("calling risk function");
        riskMethod();
    }
}