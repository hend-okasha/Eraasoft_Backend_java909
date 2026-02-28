package item.service;

import item.model.User;

public interface UserService {
	User login(String email, String password);
    Boolean signup(User user);
    User getUserByEmail(String email);

}
