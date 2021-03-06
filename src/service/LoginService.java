package service;

import exceptions.ModelNotFoundException;
import model.user.User;

public interface LoginService extends Service {
    User findUser(String login, String password) throws ModelNotFoundException;
}
