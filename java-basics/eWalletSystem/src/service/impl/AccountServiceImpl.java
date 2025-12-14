package service.impl;

import helper.AccountResult;
import model.Account;
import model.EWalletSystem;
import service.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private EWalletSystem eWalletSystem = new EWalletSystem();

    /**
     * create account
     * @param account
     * @return true if account created success - false if account already exist
     */
    @Override
    public boolean createAccount(Account account) {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);
        if (optionalAccount.isPresent()) {
            return false;
        }

        eWalletSystem.getAccounts().add(account);
        return true;
    }

    /**
     *
     * @param account
     * @return
     */
    @Override
    public boolean getAccountByUsernameAndPassword(Account account) {
        return eWalletSystem.getAccounts().stream()
                .anyMatch(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()));
    }

    @Override
    public Account getAccountByUsername(Account account) {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()){
            return null;
        }
        return optionalAccount.get();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return eWalletSystem.getAccounts().stream()
                .noneMatch(acc -> acc.getUserName().equalsIgnoreCase(username));
    }

    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        return eWalletSystem.getAccounts().stream()
                .anyMatch(acc -> acc.getPhoneNumber().equals(phoneNumber));
    }

    /**
     * @param account
     * @param amount
     * @return
     */
    @Override
    public AccountResult deposit(Account account, double amount) {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);
        if (optionalAccount.isEmpty()) {
            return new AccountResult(1);
        }

        if (amount < 100) {
            return new AccountResult(2);
        }

        Account accountToDeposit = optionalAccount.get();
        accountToDeposit.setBalance(accountToDeposit.getBalance() + amount);

        return new AccountResult(3, accountToDeposit.getBalance());
    }

    @Override
    public AccountResult withdraw(Account account, double amount) {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);
        if (optionalAccount.isEmpty()) {
            return new AccountResult(1);
        }

        if (amount < 100) {
            return new AccountResult(2);
        }

        Account accountWithdraw = optionalAccount.get();

        if (accountWithdraw.getBalance() < amount){
            return new AccountResult(3);
        }
        accountWithdraw.setBalance(accountWithdraw.getBalance() - amount);
        return new AccountResult(4, accountWithdraw.getBalance() );
    }

    /**
     *
     * @param account
     * @return
     */
    private Optional<Account> getOptionalAccountByUsername(Account account) {
        Optional<Account> optionalAccount = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName())).findFirst();

        return optionalAccount;
    }
}
