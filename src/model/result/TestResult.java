package model.result;

import model.Model;
import model.test.Answer;
import model.test.Question;
import model.test.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestResult {
    private Test test;
    private List<QuestionResult> questionResults;

    public TestResult(Test test) {
        this.test = test;
        questionResults = new ArrayList<>();
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

    public void addQuestionAnswer(Question question, Answer answer) {
        questionResults.add(new QuestionResult(question, Collections.singletonList(answer)));
    }
}
