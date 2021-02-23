package service;

import exceptions.UserNotFoundException;
import model.user.User;

public interface LoginService extends Service{
    User authorizeUser(String login, String password) throws UserNotFoundException;
}
