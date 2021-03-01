package dao.impl;

import dao.AbstractDao;
import dao.AnswerDao;
import model.test.Answer;
import model.test.Test;
import model.user.User;

import java.io.File;
import java.util.List;

public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {
    private File dataFile = new File("data", "answers.csv");

    @Override
    public List<Answer> findAnswersForTest(Test test) {
        return null;
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
    protected List<String> getModelData(Answer model) {
        return null;
    }
}
