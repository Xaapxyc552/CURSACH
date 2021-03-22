package service.impl;

import dao.DaoFactory;
import dao.QuestionDao;
import dao.TestDao;
import dao.TopicDao;
import model.test.Question;
import model.test.Test;
import service.TestService;

import java.util.List;
import java.util.stream.Collectors;

public class TestServiceImpl implements TestService {

    private QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
    private final TestDao testDao = DaoFactory.getInstance().getTestDao();

    @Override
    public List<Test> getAllTests() {
        List<Test> all = testDao.findAll();
        all.forEach(n -> n.setTopic(testDao.findTopicForTest(n)));
        return all;
    }

    @Override
    public List<Test> getAllTestsForStudents() {
        return testDao.findAll().stream()
                .filter(n -> !questionDao.getAllQuestionsForTest(n).isEmpty())
                .peek(n -> n.setTopic(testDao.findTopicForTest(n)))
                .collect(Collectors.toList());
    }

    @Override
    public Test createTest(Test test) {
        recalculateAndUpdateTest(test);
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

    @Override
    public void recalculateAndUpdateTest(Test test) {
        double maximumPoints = questionDao.getAllQuestionsForTest(test)
                .stream()
                .mapToDouble(Question::getAmountOfPoints)
                .sum();
        test.setMaximumPoints(maximumPoints);
        updateTest(test);
    }
}
