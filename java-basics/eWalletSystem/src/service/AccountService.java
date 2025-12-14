package service;

import helper.AccountResult;
import model.Account;

public interface AccountService {
    boolean createAccount(Account account);
    boolean getAccountByUsernameAndPassword(Account account);
    Account getAccountByUsername(Account account);
    boolean isUsernameUnique (String username);
    boolean isPhoneNumberExists(String phoneNumber);

    AccountResult deposit(Account account, double amount);
    AccountResult withdraw(Account account, double amount);


}
