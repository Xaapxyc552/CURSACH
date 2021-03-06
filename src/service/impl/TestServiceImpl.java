package service.impl;

import dao.DaoFactory;
import dao.TestDao;
import model.test.Test;
import service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

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
        testDao.delete(test);
        return true;
    }
}
