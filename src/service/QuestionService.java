package service;

import model.test.Question;
import model.test.Test;

import java.util.List;

public interface QuestionService extends Service{
    public Question createNewQuestion(Question question);
    public Question updateQuestionData(Question question);
    public boolean deleteQuestion(Question question);
    public List<Question> getAllQuestionsForTest(Test test);
}
