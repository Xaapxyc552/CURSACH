package dao;

import model.test.Answer;
import model.test.Test;

import java.util.List;

public interface AnswerDao extends Dao<Answer>{
    List<Answer> findAnswersForTest(Test test);
}
