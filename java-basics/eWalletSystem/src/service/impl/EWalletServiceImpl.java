package service.impl;

import exception.*;
import helper.UserInputValidator;
import model.Account;
import service.AccountService;
import service.AccountValidationService;
import service.ApplicationService;

import java.util.Scanner;

public class EWalletServiceImpl implements ApplicationService {

    private AccountService accountService = new AccountServiceImpl();
    private AccountValidationService accountValidationService = new AccountValidationServiceImpl();
    private Scanner scanner = new Scanner(System.in);
    private UserInputValidator validator;

    public EWalletServiceImpl() {
        this.validator = new UserInputValidator(scanner, accountService, accountValidationService);
    }

    @Override
    public void startApp() {
        System.out.println("Welcome to EraaSoft EWallet System");
        int invalidAttempts = 0;

        while (true) {
            try {
                System.out.println("\nPlease select your service:");
                System.out.println("1: Login    2: Signup    3: Exit");

                int service = validator.getIntInput();

                switch (service) {
                    case 1:
                        login();
                        invalidAttempts = 0;
                        break;
                    case 2:
                        signup();
                        invalidAttempts = 0;
                        break;
                    case 3:
                        System.out.println("Have a nice day :)");
                        return;
                    default:
                        System.out.println("Invalid choice! Please select 1, 2, or 3");
                        invalidAttempts++;
                        break;
                }

                if (invalidAttempts >= 3) {
                    System.out.println("Too many invalid attempts. Please contact admin.");
                    break;
                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                invalidAttempts++;
            }
        }
    }

    private void login() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.println("--- Login ---");
                System.out.print("Enter your username: ");
                String username = scanner.nextLine().trim();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                Account account = new Account(username, password);
                Account loggedInAccount = accountService.login(account);

                System.out.println("Login successful! Welcome " + loggedInAccount.getUserName());
                profile(loggedInAccount);
                return;

            } catch (InvalidCredentialsException exception) {
                attempts++;
                int remaining = MAX_ATTEMPTS - attempts;
                System.out.println("Error: " + exception.getMessage());
                if (remaining > 0) {
                    System.out.println("Remaining attempts: " + remaining);
                }
            } catch (Exception exception) {
                System.out.println("An error occurred: " + exception.getMessage());
                attempts++;
            }
        }

        System.out.println("Maximum login attempts reached. Please try again later.");
    }

    private void signup() {
        try {
            System.out.println("--- Create New Account ---");

            String username = validator.getValidatedUsername();
            if (username == null) return;

            String password = validator.getValidatedPassword();
            if (password == null) return;

            String phoneNumber = validator.getValidatedPhoneNumber();
            if (phoneNumber == null) return;

            String address = validator.getStringInput("Enter address: ");

            float age = validator.getValidatedAge();
            if (age == -1) return;

            Account account = new Account(username, password, phoneNumber, address, age);
            accountService.createAccount(account);

            System.out.println("Account created successfully!");
            profile(account);

        } catch (DuplicateAccountException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred during signup: " + exception.getMessage());
        }
    }

    private void profile(Account account) {
        int invalidAttempts = 0;

        while (true) {
            try {
                System.out.println("------------ Available Services --------------");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Show Account Details");
                System.out.println("5. Change Password");
                System.out.println("6. Logout");
                System.out.print("Select service: ");

                int choice = validator.getIntInput();

                switch (choice) {
                    case 1:
                        deposit(account);
                        invalidAttempts = 0;
                        break;
                    case 2:
                        withdraw(account);
                        invalidAttempts = 0;
                        break;
                    case 3:
                        transfer(account);
                        invalidAttempts = 0;
                        break;
                    case 4:
                        showAccountDetails(account);
                        invalidAttempts = 0;
                        break;
                    case 5:
                        changePassword(account);
                        invalidAttempts = 0;
                        break;
                    case 6:
                        System.out.println("Logged out successfully. Have a nice day :)");
                        return;
                    default:
                        System.out.println("Invalid service selection");
                        invalidAttempts++;
                        break;
                }

                if (invalidAttempts >= 4) {
                    System.out.println("Too many invalid attempts. Please contact admin.");
                    break;
                }

            } catch (Exception exception) {
                System.out.println("An error occurred: " + exception.getMessage());
                invalidAttempts++;
            }
        }
    }

    private void deposit(Account account) {
        try {
            System.out.print("Enter amount to deposit: ");
            double amount = validator.getDoubleInput();

            accountService.deposit(account, amount);

            Account updatedAccount = accountService.getAccountByUsername(account);
            System.out.println("Deposit successful! New balance: " + updatedAccount.getBalance() + " EGP");

        } catch (AccountNotFoundException | InvalidAmountException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void withdraw(Account account) {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = validator.getDoubleInput();

            accountService.withdraw(account, amount);

            Account updatedAccount = accountService.getAccountByUsername(account);
            System.out.println("Withdrawal successful! New balance: " + updatedAccount.getBalance() + " EGP");

        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred: " + exception.getMessage());
        }
    }

    private void transfer(Account sender) {
        try {
            String receiverUsername = validator.getStringInput("Enter destination username: ");

            System.out.print("Enter amount to transfer: ");
            double amount = validator.getDoubleInput();

            Account receiver = new Account(receiverUsername);
            accountService.transfer(sender, receiver, amount);

            Account updatedAccount = accountService.getAccountByUsername(sender);
            System.out.println("Transfer successful! New balance: " + updatedAccount.getBalance() + " EGP");

        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException | SelfTransferException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred: " + exception.getMessage());
        }
    }

    private void changePassword(Account account) {
        try {
            String oldPassword = validator.getStringInput("Enter old password: ");
            String newPassword = validator.getStringInput("Enter new password: ");

            accountService.changePassword(account, oldPassword, newPassword);
            System.out.println("Password changed successfully!");

        } catch (AccountNotFoundException | InvalidCredentialsException | ValidationException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred: " + exception.getMessage());
        }
    }

    private void showAccountDetails(Account account) {
        try {
            Account accountDetails = accountService.getAccountByUsername(account);
            System.out.println(accountDetails);
        } catch (AccountNotFoundException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred: " + exception.getMessage());
        }
    }
}