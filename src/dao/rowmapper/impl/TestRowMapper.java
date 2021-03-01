package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.test.Test;
import model.user.Role;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRowFromRecord(CSVRecord record) {
        return null;
    }
}
