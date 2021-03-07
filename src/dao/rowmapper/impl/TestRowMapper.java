package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.test.Test;
import model.user.Role;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRowFromRecord(CSVRecord record) {
        Test test = new Test();
        test.setId(UUID.fromString(record.get("UUID")));
        test.setName(record.get("name"));
        test.setTimeForTest(Duration.parse(record.get("timeForTest")));
        test.setMaximumPoints(Double.parseDouble(record.get("maximumPoints")));
        return test;
    }
}
