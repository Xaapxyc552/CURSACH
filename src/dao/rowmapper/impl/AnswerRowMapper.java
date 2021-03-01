package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.result.Statistics;
import model.test.Answer;
import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

public class AnswerRowMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRowFromRecord(CSVRecord record) {
        Answer answer = new Answer();
        answer.setId(UUID.fromString(record.get("UUID")));
        answer.setAnswerText(record.get("answerText"));
        answer.setCorrect(Boolean.parseBoolean(record.get("isCorrect")));
        return answer;
    }
}
