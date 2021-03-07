package service.impl;

import dao.DaoFactory;
import dao.QuestionDao;
import dao.TestDao;
import model.test.Test;
import service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

    private QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
    private final TestDao testDao = DaoFactory.getInstance().getTestDao();

    @Override
    public List<Test> getAllTests() {
        return testDao.findAll();
    }

    @Override
    public Test createTest(Test test) {
        return testDao.save(test);
    }

    @Override
    public Test updateTest(Test test) {
        return testDao.update(test);
    }

    @Override
    public boolean deleteTest(Test test) {
        questionDao.getAllQuestionsForTest(test).forEach(questionDao::delete);
        testDao.delete(test);
        return true;
    }
}
