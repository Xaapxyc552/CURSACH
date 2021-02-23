package dao;

import model.test.Question;
import model.test.Test;

import java.util.List;

public interface QuestionDao extends Dao<Question>{
    public List<Question> getAllQuestionsForTest(Test test);
    public Question getQuestionByName(String name);
}
