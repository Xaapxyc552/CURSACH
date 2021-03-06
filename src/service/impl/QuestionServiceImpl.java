package service.impl;

import dao.DaoFactory;
import dao.QuestionDao;
import model.test.Question;
import model.test.Test;
import service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();

    @Override
    public Question createNewQuestion(Question question) {
        return questionDao.save(question);
    }

    @Override
    public Question updateQuestionData(Question question) {
        return questionDao.update(question);
    }

    @Override
    public boolean deleteQuestion(Question question) {
        questionDao.delete(question);
        return true;
    }

    @Override
    public List<Question> getAllQuestionsForTest(Test test) {
        List<Question> allQuestionsForTest = questionDao.getAllQuestionsForTest(test);
        allQuestionsForTest.forEach(n -> n.setTest(test));
        return allQuestionsForTest;
    }
}
