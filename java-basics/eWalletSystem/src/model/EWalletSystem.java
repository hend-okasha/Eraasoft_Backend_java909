package model;

import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {

    private static EWalletSystem eWalletSystem;
    private final String name = "Eraasoft E Wallet System";
    private List<Account> accounts = new ArrayList<>();

    private EWalletSystem() {
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
}
