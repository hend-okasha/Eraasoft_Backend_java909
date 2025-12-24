package helper;

import service.AccountService;
import service.AccountValidationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputValidator {

    private Scanner scanner;
    private AccountService accountService;
    private AccountValidationService accountValidationService;

    public UserInputValidator(Scanner scanner, AccountService accountService,
                              AccountValidationService accountValidationService) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.accountValidationService = accountValidationService;
    }

    public String getValidatedUsername() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (!accountService.isUsernameUnique(username)) {
            System.out.println("Error: Username already exists");
            return null;
        }

        Integer usernameError = accountValidationService.validateUserName(username);
        if (usernameError == 1) {
            System.out.println("Error: Username must be at least 3 characters");
            return null;
        } else if (usernameError == 2) {
            System.out.println("Error: First letter must be capital");
            return null;
        }

        return username;
    }

    public String getValidatedPassword() {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Integer passwordError = accountValidationService.validatePassword(password);

        switch (passwordError) {
            case 1:
                System.out.println("Error: Password must be at least 8 characters");
                return null;
            case 2:
                System.out.println("Error: Password must contain uppercase letter");
                return null;
            case 3:
                System.out.println("Error: Password must contain lowercase letter");
                return null;
            case 4:
                System.out.println("Error: Password must contain digit");
                return null;
            case 5:
                System.out.println("Error: Password must contain special character");
                return null;
        }

        return password;
    }

    public String getValidatedPhoneNumber() {
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        Integer phoneError = accountValidationService.validatePhoneNumber(phoneNumber);

        switch (phoneError) {
            case 1:
                System.out.println("Error: Phone number must be 11 digits");
                return null;
            case 2:
                System.out.println("Error: Phone number must contain digits only");
                return null;
            case 3:
                System.out.println("Error: Phone number must start with 01");
                return null;
            case 4:
                System.out.println("Error: Invalid Egyptian mobile operator");
                return null;
        }

        if (accountService.isPhoneNumberExists(phoneNumber)) {
            System.out.println("Error: Phone number already exists");
            return null;
        }

        return phoneNumber;
    }

    public float getValidatedAge() {
        try {
            System.out.print("Enter age: ");
            float age = scanner.nextFloat();
            scanner.nextLine();

            if (age < 18) {
                System.out.println("Error: Must be 18 years or older");
                return -1;
            }

            return age;

        } catch (InputMismatchException exception) {
            System.out.println("Error: Please enter a valid age");
            scanner.nextLine();
            return -1;
        }
    }

    public int getIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException exception) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.nextLine();
            }
        }
    }

    public double getDoubleInput() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException exception) {
                System.out.print("Invalid input! Please enter a valid amount: ");
                scanner.nextLine();
            }
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
