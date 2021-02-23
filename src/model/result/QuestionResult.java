package model.result;

import model.test.Answer;
import model.test.Question;

import java.util.List;

public class QuestionResult {
    private Question question;
    private List<Answer> providedAnswers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getProvidedAnswers() {
        return providedAnswers;
    }

    public void setProvidedAnswers(List<Answer> providedAnswers) {
        this.providedAnswers = providedAnswers;
    }
}
