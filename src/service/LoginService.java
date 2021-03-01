package service;

import exceptions.ModelNotFoundException;
import model.user.User;

public interface LoginService extends Service{
    User authorizeUser(String login, String password) throws ModelNotFoundException;
}
