package service.impl;

import dao.AnswerDao;
import dao.DaoFactory;
import dao.QuestionDao;
import model.test.Question;
import model.test.Test;
import service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
    private AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

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
        answerDao.findAnswersForQuestion(question).forEach(answerDao::delete);
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
