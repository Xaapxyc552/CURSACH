package dao.impl;

import dao.*;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.QuestionRowMapper;
import model.test.Question;
import model.test.Test;

import java.io.File;
import java.util.List;

public class QuestionDaoImpl extends AbstractDao <Question> implements QuestionDao {
    private File dataFile = new File("data", "questions.csv");

    private TestDao testDao = DaoFactory.getInstance().getTestDao();
    private AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

    @Override
    public List<Question> getAllQuestionsForTest(Test test) {
        return null;
    }

    @Override
    public Question getQuestionByName(String name) {
        return null;
    }

    @Override
    protected RowMapper<Question> getRowMapper() {
        return new QuestionRowMapper();
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[0];
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Question model) {
        return null;
    }
}
