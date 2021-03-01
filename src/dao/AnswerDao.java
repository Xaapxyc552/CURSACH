package dao;

import model.test.Answer;
import model.test.Question;
import model.test.Test;

import java.util.List;

public interface AnswerDao extends Dao<Answer>{
    List<Answer> findAnswersForQuestion(Question question);
}
