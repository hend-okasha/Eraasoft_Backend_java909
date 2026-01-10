package service;

import exception.*;
import model.Account;
import model.History;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountService {
    void createAccount(Account account)
            throws DuplicateAccountException;

    Account login(Account account)
            throws InvalidCredentialsException, InactiveAccountException;

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

    boolean isAdmin(Account account);

    void deleteAccountByAdmin(String username, Account admin)
            throws AccountNotFoundException;

    void inActivateAccount(String username, Account admin)
            throws AccountNotFoundException;

    void activateAccount(String username, Account admin)
            throws AccountNotFoundException;

    List<Account> getAllAccounts();

    Map<String, List<History>> getSystemHistory();

}
