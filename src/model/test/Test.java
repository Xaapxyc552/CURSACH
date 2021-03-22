package model.test;

import front.util.DurationUtils;
import model.Model;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class Test implements Model {
    private UUID id;
    private String name;
    private Topic topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
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

    @Override
    public String toString() {
        return topic + ". " + name + ". Час на тест: " +
                DurationUtils.formatDuration(timeForTest);
    }


}
