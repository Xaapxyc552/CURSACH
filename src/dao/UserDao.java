package dao;

import model.result.Statistics;
import model.user.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User getUserByLogin(String login);

    User getTeacherByLogin(String login);

    User getStudentByLogin(String login);

    List<User> findAllStudents();
}
