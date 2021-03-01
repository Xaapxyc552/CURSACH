package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.user.Role;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRowFromRecord(CSVRecord record) {
        User user = new User();
        user.setId(UUID.fromString(record.get("UUID")));
        user.setLogin(record.get("login"));
        user.setPassword(record.get("password"));
        user.setName(record.get("name"));
        user.setSurname(record.get("surname"));
        user.setRole(Role.valueOf(record.get("role")));
        return user;
    }
}
