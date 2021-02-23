package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import exceptions.UserNotFoundException;
import model.user.User;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public User authorizeUser(String login, String password) throws UserNotFoundException {
        return null;
    }
}
