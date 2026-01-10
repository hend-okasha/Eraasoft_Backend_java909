package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EWalletSystem {

    private static EWalletSystem eWalletSystem;
    private final String name = "Eraasoft E-Wallet System";
    private List<Account> accounts = new ArrayList<>();
    private Map<String, List<History>> systemHistory = new HashMap<>();


    private EWalletSystem() {
        createAdmin();
    }

    private void createAdmin() {
        boolean isAdminExists = accounts.stream()
                .anyMatch( acc -> acc.getUserName().equals("IamAdmin") && acc.isAdmin());

        if (!isAdminExists){
            Account admin = new Account("IamAdmin", "Admin@1234", true);
            accounts.add(admin);
            System.out.println("Admin account created successfully (Username: IamAdmin, Password: Admin@1234)");
        }
    }

    public static synchronized EWalletSystem getInstance() {
        if (eWalletSystem == null) {
            eWalletSystem = new EWalletSystem();
        }
        return eWalletSystem;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Map<String, List<History>> getSystemHistory() {
        return systemHistory;
    }

    public void setSystemHistory(Map<String, List<History>> systemHistory) {
        this.systemHistory = systemHistory;
    }
}
