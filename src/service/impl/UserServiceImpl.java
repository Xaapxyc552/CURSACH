package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import model.user.Role;
import model.user.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public User registerNewUser(String login, String password, Role role) {
        return null;
    }

    @Override
    public User updateUserData(User user) {
        return null;
    }
}
