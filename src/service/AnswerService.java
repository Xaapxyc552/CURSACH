package service;

import model.test.Answer;
import model.test.Question;

import java.util.List;

public interface AnswerService extends Service{
    public Answer createNewAnswer(Answer question);
    public Answer updateAnswerData(Answer question);
    public boolean deleteAnswer(Answer question);
    public List<Answer> getAllAnswersForQuestion(Question question);
}
