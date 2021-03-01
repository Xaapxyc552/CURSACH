package dao.impl;

import dao.AbstractDao;
import dao.UserDao;
import model.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao <User> implements UserDao {
    private static final File dataFile = new File("data", "users.csv");

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

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","login","password","name","surname","role"};
    }

    @Override
    protected List<String> getModelData(User model) {
        List<String> modelData = new ArrayList<>();
        modelData.add(model.getId().toString());
        modelData.add(model.getLogin());
        modelData.add(model.getPassword());
        modelData.add(model.getName());
        modelData.add(model.getSurname());
        modelData.add(model.getRole().toString());
        return modelData;
    }
}
