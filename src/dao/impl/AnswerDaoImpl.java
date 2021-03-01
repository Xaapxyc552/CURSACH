package dao.impl;

import dao.AbstractDao;
import dao.AnswerDao;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.AnswerRowMapper;
import model.test.Answer;
import model.test.Question;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {
    private File dataFile = new File("data", "answers.csv");

    @Override
    public List<Answer> findAnswersForQuestion(Question question) {
        Predicate<CSVRecord> predicate = n -> n.get("questionId").equals(question.getId().toString());
        return findMultipleModelsByPredicate(predicate);
    }

    @Override
    protected RowMapper<Answer> getRowMapper() {
        return new AnswerRowMapper();
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","questionId","answerText","isCorrect"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Answer model) {
        ArrayList<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getQuestion().getId().toString());
        data.add(model.getAnswerText());
        data.add(String.valueOf(model.isCorrect()));
        return data;
    }
}
