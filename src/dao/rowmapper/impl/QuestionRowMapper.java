package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.test.Question;
import model.test.Test;
import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRowFromRecord(CSVRecord record) {
        Question question = new Question();
        question.setId(UUID.fromString(record.get("UUID")));
        question.setName(record.get("name"));
        question.setQuestionText(record.get("questionText"));
        question.setAmountOfPoints(Double.parseDouble(record.get("amountOfPoints")));
        return null;
    }
}
