package service.impl;

import dao.AnswerDao;
import dao.DaoFactory;
import model.test.Answer;
import model.test.Question;
import service.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

    @Override
    public Answer createNewAnswer(Answer answer) {
        return answerDao.save(answer);
    }

    @Override
    public Answer updateAnswerData(Answer answer) {
        return answerDao.update(answer);
    }

    @Override
    public boolean deleteAnswer(Answer answer) {
        answerDao.delete(answer);
        return true;
    }

    @Override
    public List<Answer> getAllAnswersForQuestion(Question question) {
        List<Answer> answersForQuestion = answerDao.findAnswersForQuestion(question);
        answersForQuestion.forEach(n -> n.setQuestion(question));
        return answersForQuestion;
    }
}
