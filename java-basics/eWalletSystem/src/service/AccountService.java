package service;

import exception.*;
import model.Account;

public interface AccountService {
    void createAccount(Account account)
            throws DuplicateAccountException;

    Account login(Account account)
            throws InvalidCredentialsException;

    Account getAccountByUsername(Account account)
            throws AccountNotFoundException;

    boolean isUsernameUnique(String username);

    boolean isPhoneNumberExists(String phoneNumber);

    void deposit(Account account, double amount)
            throws AccountNotFoundException, InvalidAmountException;

    void withdraw(Account account, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException;

    void transfer(Account sender, Account receiver, double amount)
            throws AccountNotFoundException, InvalidAmountException,
            InsufficientBalanceException, SelfTransferException;

    void changePassword(Account account, String oldPassword, String newPassword)
            throws AccountNotFoundException, InvalidCredentialsException, ValidationException;
}
