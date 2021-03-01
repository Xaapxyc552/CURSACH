package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.result.Statistics;
import model.test.Answer;
import org.apache.commons.csv.CSVRecord;

public class AnswerRowMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRowFromRecord(CSVRecord record) {
        return null;
    }
}
