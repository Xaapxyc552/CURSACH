package model.test;

import model.Model;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class Test implements Model {
    private UUID id;
    private String name;
    private String topic;
    private List<Question> questions;
    private Duration timeForTest;
    private double maximumPoints;

    public double getMaximumPoints() {
        return maximumPoints;
    }

    public void setMaximumPoints(double maximumPoints) {
        this.maximumPoints = maximumPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Duration getTimeForTest() {
        return timeForTest;
    }

    public void setTimeForTest(Duration timeForTest) {
        this.timeForTest = timeForTest;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
