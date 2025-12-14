package service;

public interface AccountValidationService {
    Integer validateUserName(String userName);
    Integer validatePassword(String password);
    Integer validatePhoneNumber(String phoneNumber);

}
