package dao.impl;

import dao.*;
import model.test.Question;
import model.test.Test;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private TestDao testDao = DaoFactory.getInstance().getTestDao();
    private AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

    @Override
    public Question save(Question model) {
        return null;
    }

    @Override
    public Question update(Question model) {
        return null;
    }

    @Override
    public void delete(Question model) {

    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public Question findById(long id) {
        return null;
    }

    @Override
    public List<Question> getAllQuestionsForTest(Test test) {
        return null;
    }

    @Override
    public Question getQuestionByName(String name) {
        return null;
    }
}
