package service;

import model.result.Statistics;
import model.user.Role;
import model.user.User;

import java.util.List;

public interface UserService extends Service{

    User registerNewUser(User user);

    User updateUserData(User user);

    List<User> findAllStudents();
}
