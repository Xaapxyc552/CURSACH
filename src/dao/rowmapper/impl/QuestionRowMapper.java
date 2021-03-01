package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.test.Question;
import model.test.Test;
import org.apache.commons.csv.CSVRecord;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRowFromRecord(CSVRecord record) {
        return null;
    }
}
