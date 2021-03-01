package model.test;

import model.Model;

import java.util.List;
import java.util.UUID;

public class Question implements Model {
    private UUID id;
    private Test test;
    private String name;
    private String questionText;
    private List<Answer> answerList;
    private double amountOfPoints;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public double getAmountOfPoints() {
        return amountOfPoints;
    }

    public void setAmountOfPoints(double amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
