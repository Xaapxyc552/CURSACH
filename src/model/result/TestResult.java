package model.result;

import model.Model;
import model.test.Answer;
import model.test.Question;
import model.test.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestResult {
    private Test test;
    private List<QuestionResult> questionResults;
    private LocalDateTime finishTime;
    private LocalDateTime startTime;

    public TestResult(Test test, LocalDateTime startTime) {
        this.test = test;
        questionResults = new ArrayList<>();
        this.startTime = startTime;
    }

    public void addQuestionAnswer(Question question, Answer answer) {
        questionResults.add(new QuestionResult(question, Collections.singletonList(answer)));
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

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


}
