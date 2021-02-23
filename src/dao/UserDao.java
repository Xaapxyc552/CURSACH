package dao;

import model.user.User;

public interface UserDao extends Dao<User> {
    User getUserByLogin(String login);

    User getTeacherByLogin(String login);

    User getStudentByLogin(String login);

}
