package service.impl;

import helper.AccountResult;
import model.Account;
import service.AccountService;
import service.AccountValidationService;
import service.ApplicationService;

import java.util.Objects;
import java.util.Scanner;

public class EWalletServiceImpl implements ApplicationService {

    private AccountService accountService = new AccountServiceImpl();

    private AccountValidationService accountValidationService = new AccountValidationServiceImpl();

    @Override
    public void startApp() {
        System.out.println("welcome to EraaSoft EWallet system");
        boolean isExit = false;
        int counter = 0;

        while(true) {
            System.out.println(" please enter your service:");
            System.out.println("1: login           2: signup              3: Exit");
            Scanner scanner = new Scanner(System.in);
            int service = scanner.nextInt();

            switch (service) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.out.println("have a nice day :)");
                    isExit = true;
                    break;
                default:
                    System.out.println("invalid choice :(");
                    counter++;
                    break;
            }
            if (isExit){
                break;
            }

            if (counter == 3){
                System.out.println("please contact with admin ");
                break;
            }
        }
    }

    private void login(){
        Account account= getAccount(true);

        if (Objects.isNull(account)){
            return;
        }
        boolean loginSuccess = accountService.getAccountByUsernameAndPassword(account);
        if (loginSuccess){
            System.out.println(" successful login...");
            profile(account);
        }else {
            System.out.println("invalid username or password...");
        }


    }

    private void signup(){
        Account account= getAccount(false);

        if (Objects.isNull(account)){
            return;
        }

        boolean isAccountCreated = accountService.createAccount(account);

        if (isAccountCreated){
            System.out.println(" Account created successfully....");
            profile(account);
        } else {
            System.out.println(" Account already exist with same username " + account.getUserName());
        }
    }

    private Account getAccount(boolean login){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username : ");
        String userName = scanner.nextLine();

        if (!login) {
            if (!accountService.isUsernameUnique(userName)) {
                System.out.println("this username already exist");
                return null;
            }
        } else {
            if (accountService.isUsernameUnique(userName)) {
                System.out.println("username not found");
                return null;
            }
        }

        Integer usernameError = accountValidationService.validateUserName(userName);

        if (usernameError == 2) {
            System.out.println("first letter must be capital");
            return null;
        } else if (usernameError == 1) {
            System.out.println("username must be greater than 3 letters");
            return null;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Integer passwordError = accountValidationService.validatePassword(password);

        if (passwordError == 1) {
            System.out.println("password must be at least 8 characters");
            return null;
        } else if (passwordError == 2) {
            System.out.println("password must contain uppercase letter");
            return null;
        } else if (passwordError == 3) {
            System.out.println("password must contain lowercase letter");
            return null;
        } else if (passwordError == 4) {
            System.out.println("password must contain digit");
            return null;
        } else if (passwordError == 5) {
            System.out.println("password must contain special character");
            return null;
        }

        if (login){
            return new Account(userName,password);
        }

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Integer phoneError = accountValidationService.validatePhoneNumber(phoneNumber);

        if (phoneError == 1) {
            System.out.println("phone number must be 11 digits");
            return null;
        } else if (phoneError == 2) {
            System.out.println("phone number must contain digits only");
            return null;
        } else if (phoneError == 3) {
            System.out.println("phone number must start with 01");
            return null;
        } else if (phoneError == 4) {
            System.out.println("invalid Egyptian mobile operator");
            return null;
        }
        if (accountService.isPhoneNumberExists(phoneNumber)) {
            System.out.println("phone number already exists");
            return null;
        }

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter age: ");
        float age = scanner.nextFloat();
        if (age < 18){
            System.out.println("under 18 not allowed");
            return null;
        }

        return new Account(userName, password, phoneNumber, address, age);
    }

    private void profile(Account account){
        boolean logout = false;
        int counter = 0;

       while (true){
           System.out.println("------------Available services--------------");
           System.out.println("1.deposit      2.withdraw          3.show account details     4.logout");
           Scanner scanner= new Scanner(System.in);
           System.out.println("please give me a service you want to apply:");
           int result = scanner.nextInt();

           switch (result){
               case 1:
                   deposit(account);
                   break;
               case 2:
                   withdraw(account);
                   break;
               case 3:
                   showAccountDetails(account);
                   break;
               case 4:
                   System.out.println("have a nice day :)");
                   logout = true;
                   break;
               default:
                   System.out.println("invalid service");
                   counter ++;
           }
           if (logout){
               break;
           }

           if (counter == 4){
               System.out.println("please contact with admin...");
               break;
           }
       }

    }

    private void showAccountDetails(Account account) {
        Account accountExist = accountService.getAccountByUsername(account);

        if (Objects.isNull(accountExist)){
            System.out.println(" Account not found");
            return;
        }

        System.out.println(account);

    }

    private void withdraw(Account account) {

        System.out.println("please enter amount you need to withdraw:");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();

        AccountResult withdrawSuccess = accountService.withdraw(account , amount);

        Integer error = withdrawSuccess.getError();
        if (error== 4){
            System.out.println(" withdraw success your balance : " + withdrawSuccess.getAmount());
        } else if (error == 3) {
            System.out.println("amount you need to withdraw must be greater than your balance");
        } else if (error == 2) {
            System.out.println("amount must be greater than 100");
        } else if (error == 1) {
            System.out.println("account not exist :(");
        }
    }

    private void deposit(Account account) {
        System.out.println("please enter amount you need to deposit:");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();

        AccountResult depositSuccess = accountService.deposit(account , amount);

        Integer error = depositSuccess.getError();
        if (error == 3){
            System.out.println(" deposit success your balance :" + depositSuccess.getAmount());
        } else if (error == 2) {
            System.out.println("amount must be greater than 100");
        } else if (error == 1) {
            System.out.println("account not exist");
        }


    }
}
