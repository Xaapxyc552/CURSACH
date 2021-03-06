package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import exceptions.ModelNotFoundException;
import model.user.User;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public User findUser(String login, String password) throws ModelNotFoundException {
        return userDao.getUserByLogin(login);
    }
}
