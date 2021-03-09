package service.impl;

import dao.DaoFactory;
import dao.UserDao;
import model.result.Statistics;
import model.user.Role;
import model.user.User;
import service.UserService;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public User registerNewUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUserData(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> findAllStudents() {
        return userDao.findAllStudents();
    }

}
