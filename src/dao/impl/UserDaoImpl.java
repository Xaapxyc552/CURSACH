package dao.impl;

import dao.UserDao;
import model.user.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User save(User model) {
        return null;
    }

    @Override
    public User update(User model) {
        return null;
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User getTeacherByLogin(String login) {
        return null;
    }

    @Override
    public User getStudentByLogin(String login) {
        return null;
    }
}
