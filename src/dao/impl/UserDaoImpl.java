package dao.impl;

import dao.AbstractDao;
import dao.UserDao;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.UserRowMapper;
import model.user.Role;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserDaoImpl extends AbstractDao <User> implements UserDao {
    private static final File dataFile = new File("data", "users.csv");

    @Override
    public User getUserByLogin(String login) {
        Predicate<CSVRecord> loginPredicate = n -> n.get("login").equals(login);
        return findSingleModelByPredicate(loginPredicate);
    }

    @Override
    public User getTeacherByLogin(String login) {
        Predicate<CSVRecord> loginPredicate = n -> n.get("login").equals(login);
        Predicate<CSVRecord> finalPredicate = loginPredicate.and(n -> n.get("role").equals(Role.TEACHER.name()));
        return findSingleModelByPredicate(finalPredicate);
    }

    @Override
    public User getStudentByLogin(String login) {
        Predicate<CSVRecord> loginPredicate = n -> n.get("login").equals(login);
        Predicate<CSVRecord> finalPredicate = loginPredicate.and(n -> n.get("role").equals(Role.STUDENT.name()));
        return findSingleModelByPredicate(finalPredicate);
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
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
