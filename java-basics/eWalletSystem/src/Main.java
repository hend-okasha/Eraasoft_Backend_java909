import service.ApplicationService;
import service.impl.EWalletServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationService applicationService = new EWalletServiceImpl();
        applicationService.startApp();

    }
}