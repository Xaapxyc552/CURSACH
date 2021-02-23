package model.result;

import model.Model;
import model.test.Test;

import java.util.List;

public class TestResult {
    private Test test;
    private List<QuestionResult> questionResults;

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
}
