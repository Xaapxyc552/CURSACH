package model.test;

import model.Model;

import java.util.UUID;

public class Answer implements Model {
    private UUID id;
    private Question question;
    private String answerText;
    private boolean isCorrect;

    @Override
    public String toString() {
        return answerText + ". Правильний: " + (isCorrect ? "Так" : "Ні");
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
