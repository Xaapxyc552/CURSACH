package service;

import model.user.Role;
import model.user.User;

public interface UserService extends Service{

    User registerNewUser(String login, String password, Role role);

    User updateUserData(User user);
}
