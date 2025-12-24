package service.impl;

import exception.*;
import model.Account;
import model.EWalletSystem;
import service.AccountService;
import service.AccountValidationService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private EWalletSystem eWalletSystem = EWalletSystem.getInstance();  // ← غير السطر ده

    @Override
    public void createAccount(Account account) throws DuplicateAccountException {
        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);
        if (optionalAccount.isPresent()) {
            throw new DuplicateAccountException(
                    "Account with username '" + account.getUserName() + "' already exists"
            );
        }
        eWalletSystem.getAccounts().add(account);
    }

    @Override
    public Account login(Account account) throws InvalidCredentialsException {
        Optional<Account> foundAccount = eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst();

        if (foundAccount.isEmpty()) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

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
            throw new AccountNotFoundException("Account not found");
        }

        if (amount < 100) {
            throw new InvalidAmountException("Minimum deposit amount is 100 EGP");
        }

        Account accountToDeposit = optionalAccount.get();
        accountToDeposit.setBalance(accountToDeposit.getBalance() + amount);
    }

    @Override
    public void withdraw(Account account, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        Optional<Account> optionalAccount = getOptionalAccountByUsername(account);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }

        if (amount < 100) {
            throw new InvalidAmountException("Minimum withdrawal amount is 100 EGP");
        }

        Account accountWithdraw = optionalAccount.get();

        if (accountWithdraw.getBalance() < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Your balance: " + accountWithdraw.getBalance() + " EGP"
            );
        }

        accountWithdraw.setBalance(accountWithdraw.getBalance() - amount);
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

        if (receiverOptionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Receiver account not found");
        }

        Account senderAccount = senderOptionalAccount.get();
        Account receiverAccount = receiverOptionalAccount.get();

        if (senderAccount.getUserName().equals(receiverAccount.getUserName())) {
            throw new SelfTransferException("Cannot transfer to yourself");
        }

        if (amount <= 100) {
            throw new InvalidAmountException("Minimum transfer amount is 100 EGP");
        }

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Your balance: " + senderAccount.getBalance() + " EGP"
            );
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
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
}
