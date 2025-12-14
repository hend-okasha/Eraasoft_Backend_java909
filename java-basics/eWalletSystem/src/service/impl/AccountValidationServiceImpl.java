package service.impl;

import service.AccountValidationService;

public class AccountValidationServiceImpl implements AccountValidationService {
    @Override
    public Integer validateUserName(String userName) {
        if (userName.length() < 3){
            return 1;
        }

        if (!Character.isUpperCase(userName.charAt(0))){
            return 2;
        }

        return 3;
    }

    @Override
    public Integer validatePassword(String password) {
        if (password.length() < 8){
            return 1;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char character : password.toCharArray()) {

            if (Character.isUpperCase(character)) {
                hasUpper = true;
            } else if (Character.isLowerCase(character)) {
                hasLower = true;
            } else if (Character.isDigit(character)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (!hasUpper) return 2;
        if (!hasLower) return 3;
        if (!hasDigit) return 4;
        if (!hasSpecial) return 5;

        return 6;
    }

    @Override
    public Integer validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.length() != 11){
            return 1;
        }

        for (char digit : phoneNumber.toCharArray()){
            if (! Character.isDigit(digit)){
                return 2;
            }
        }

        if (phoneNumber.charAt(0) != '0' ||phoneNumber.charAt(1) != '1'){
            return 3;
        }

        char thirdDigit = phoneNumber.charAt(2);
        if (thirdDigit != '0' &&
                thirdDigit != '1' &&
                thirdDigit != '2' &&
                thirdDigit != '5') {
            return 4;
        }
        return 0;
    }
}
