package service.impl;

import exception.*;
import model.Account;
import model.EWalletSystem;
import model.History;
import service.AccountService;
import service.AccountValidationService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private EWalletSystem eWalletSystem = EWalletSystem.getInstance();

    @Override
    public void createAccount(Account account) throws DuplicateAccountException {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isPresent()) {
            addHistory(account,"SignUP", "failed","Account already exists");
            throw new DuplicateAccountException(
                    "Account with username '" + account.getUserName() + "' already exists"
            );
        }
        eWalletSystem.getAccounts().add(account);
        addHistory(account ,"SignUP", "Success","Account signed UP successfully");
    }

    @Override
    public Account login(Account account) throws InvalidCredentialsException, InactiveAccountException {
        Optional<Account> foundAccount = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst();

        if (foundAccount.isEmpty()) {
            addHistory(account,"LogIN", "failed","invalid username or password");
            throw new InvalidCredentialsException("Invalid username or password");
        }

        Account loggedAccount = foundAccount.get();

        if (!loggedAccount.isActive()) {
            throw new InactiveAccountException("Your account is inactive. Please contact admin.");
        }

        addHistory(loggedAccount ,"login", "success","Account logged in successfully");
        return foundAccount.get();
    }

    @Override
    public Account getAccountByUsername(Account account) throws AccountNotFoundException {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
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

    @Override
    public void deposit(Account account, double amount)
            throws AccountNotFoundException, InvalidAmountException {

        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()) {
            addHistory(account, "Deposit", amount, "failed", "Transaction failed: Account not found");
            throw new AccountNotFoundException("Account not found");
        }

        Account accountToDeposit = optionalAccount.get();

        if (amount < 100) {
            addHistory(accountToDeposit, "Deposit", amount, "failed", "Transaction failed: Minimum deposit amount is 100 EGP");
            throw new InvalidAmountException("Minimum deposit amount is 100 EGP");
        }

        accountToDeposit.setBalance(accountToDeposit.getBalance() + amount);
        addHistory(accountToDeposit, "Deposit", amount, "success", "Deposited successfully. New balance: " + accountToDeposit.getBalance() + " EGP");
    }

    @Override
    public void withdraw(Account account, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()) {
            addHistory(account, "WITHDRAW", amount, "FAILED",
                    "Account not found");
            throw new AccountNotFoundException("Account not found");
        }

        Account accountWithdraw = optionalAccount.get();

        if (amount < 100) {
            addHistory(accountWithdraw, "WITHDRAW", amount, "FAILED",
                    "Minimum withdrawal amount is 100 EGP");
            throw new InvalidAmountException("Minimum withdrawal amount is 100 EGP");
        }

        if (accountWithdraw.getBalance() < amount) {
            addHistory(account, "WITHDRAW", amount, "FAILED",
                    "Insufficient balance. Your balance: " + accountWithdraw.getBalance() + " EGP");
            throw new InsufficientBalanceException(
                    "Insufficient balance. Your balance: " + accountWithdraw.getBalance() + " EGP");
        }

        accountWithdraw.setBalance(accountWithdraw.getBalance() - amount);
        addHistory(accountWithdraw, "WITHDRAW", amount, "SUCCESS",
                "Withdrawn successfully. New balance: " + accountWithdraw.getBalance() + " EGP");
    }

    @Override
    public void transfer(Account sender, Account receiver, double amount)
            throws AccountNotFoundException, InvalidAmountException,
            InsufficientBalanceException, SelfTransferException {

        Optional<Account> senderOptionalAccount = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(sender.getUserName()))
                .findFirst();

        if (senderOptionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Sender account not found");
        }

        Optional<Account> receiverOptionalAccount = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(receiver.getUserName()))
                .findFirst();

        Account senderAccount = senderOptionalAccount.get();

        if (receiverOptionalAccount.isEmpty()) {
            addHistory(senderAccount, "TRANSFER", amount, "FAILED",
                    "Receiver account not found");
            throw new AccountNotFoundException("Receiver account not found");
        }

        Account receiverAccount = receiverOptionalAccount.get();

        if (senderAccount.getUserName().equals(receiverAccount.getUserName())) {
            addHistory(senderAccount, "TRANSFER", amount, "FAILED",
                    "Cannot transfer to yourself");
            throw new SelfTransferException("Cannot transfer to yourself");
        }

        if (amount <= 100) {
            addHistory(senderAccount, "TRANSFER", amount, "FAILED",
                    "Minimum transfer amount is 100 EGP");
            throw new InvalidAmountException("Minimum transfer amount is 100 EGP");
        }

        if (senderAccount.getBalance() < amount) {
            addHistory(senderAccount, "TRANSFER", amount, "FAILED",
                    "Insufficient balance. Your balance: " + senderAccount.getBalance() + " EGP");
            throw new InsufficientBalanceException(
                    "Insufficient balance. Your balance: " + senderAccount.getBalance() + " EGP"
            );
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        addHistory(senderAccount, "TRANSFER", amount, "SUCCESS",
                "Transferred to " + receiverAccount.getUserName() +
                        ". New balance: " + senderAccount.getBalance() + " EGP");

        addHistory(senderAccount, "TRANSFER", amount, "SUCCESS",
                "Transferred to " + receiverAccount.getUserName() +
                        ". New balance: " + senderAccount.getBalance() + " EGP");
    }

    @Override
    public void changePassword(Account account, String oldPassword, String newPassword)
            throws AccountNotFoundException, InvalidCredentialsException, ValidationException {

        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }

        Account accountChangePassword = optionalAccount.get();

        if (!accountChangePassword.getPassword().equals(oldPassword)) {
            throw new InvalidCredentialsException("Old password is incorrect");
        }

        AccountValidationService validation = new AccountValidationServiceImpl();
        Integer passwordError = validation.validatePassword(newPassword);

        if (passwordError != 6) {
            String errorMessage = getPasswordErrorMessage(passwordError);
            throw new ValidationException("password", errorMessage);
        }

        if (oldPassword.equals(newPassword)) {
            throw new ValidationException("password", "New password cannot be same as old password");
        }

        accountChangePassword.setPassword(newPassword);
        addHistory(accountChangePassword, "PASSWORD_CHANGE", "SUCCESS",
                "Password changed successfully");
    }

    @Override
    public boolean isAdmin(Account account) {
        try{
            Account foundAccount = getAccountByUsername(account);
            return foundAccount.isAdmin();
        }catch (AccountNotFoundException exception){
            return false;
        }
    }

    @Override
    public void deleteAccountByAdmin(String username, Account admin) throws AccountNotFoundException {
        if (!admin.isAdmin()) {
            throw new AccountNotFoundException("Only admin can delete accounts");
        }

        if (admin.getUserName().equals(username)) {
            throw new AccountNotFoundException("Admin cannot delete their own account");
        }

        Optional<Account> accountToDelete = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(username))
                .findFirst();

        if (accountToDelete.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + username);
        }

        Account account = accountToDelete.get();

        addHistory(account, "ACCOUNT_DELETED", "SUCCESS",
                "Account deleted by admin: " + admin.getUserName());

        eWalletSystem.getAccounts().remove(account);

    }

    @Override
    public void inActivateAccount(String username, Account admin) throws AccountNotFoundException {
        if (!admin.isAdmin()) {
            throw new AccountNotFoundException("Only admin can inactivate accounts");
        }

        if (admin.getUserName().equals(username)) {
            throw new AccountNotFoundException("Admin cannot inactivate their own account");
        }

        Optional<Account> accountToInactivate = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(username))
                .findFirst();

        if (accountToInactivate.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + username);
        }

        Account account = accountToInactivate.get();
        account.setActive(false);

        addHistory(account, "ACCOUNT_INACTIVATED", "SUCCESS",
                "Account inactivated by admin: " + admin.getUserName());
    }

    @Override
    public void activateAccount(String username, Account admin) throws AccountNotFoundException {
        if (!admin.isAdmin()) {
            throw new AccountNotFoundException("Only admin can activate accounts");
        }

        Optional<Account> accountToActivate = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(username))
                .findFirst();

        if (accountToActivate.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + username);
        }

        Account account = accountToActivate.get();
        account.setActive(true);

        addHistory(account, "ACCOUNT_ACTIVATED", "SUCCESS",
                "Account activated by admin: " + admin.getUserName());
    }

    @Override
    public List<Account> getAllAccounts() {
        return eWalletSystem.getAccounts();
    }

    @Override
    public Map<String, List<History>> getSystemHistory() {
        Map<String, List<History>> systemHistory = eWalletSystem.getSystemHistory();

        for (Account account : eWalletSystem.getAccounts()) {
            systemHistory.put(account.getUserName(), account.getHistory());
        }

        return systemHistory;
    }

    private String getPasswordErrorMessage(Integer error) {
        switch (error) {
            case 1: return "Password must be at least 8 characters";
            case 2: return "Password must contain uppercase letter";
            case 3: return "Password must contain lowercase letter";
            case 4: return "Password must contain digit";
            case 5: return "Password must contain special character";
            default: return "Invalid password format";
        }
    }

    private Optional<Account> getOptionalAccountByUsername(Account account) {
        return eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst();
    }

    private void addHistory(Account account, String action, Double amount, String status, String description){
        History history = new History(action, amount, status, description);
        account.getHistory().add(history);

        Map<String, List<History>> systemHistory = eWalletSystem.getSystemHistory();
        systemHistory.computeIfAbsent(account.getUserName(), key -> account.getHistory());
    }

    private void addHistory(Account account, String action, String status, String description){
        History history = new History(action, status, description);
        account.getHistory().add(history);

        Map<String, List<History>> systemHistory = eWalletSystem.getSystemHistory();
        systemHistory.computeIfAbsent(account.getUserName(), k -> account.getHistory());
    }

}
