package model.test;

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
        return topic + ". " + name + ". Час на тест: " + formatDuration(timeForTest);
    }

    private static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
}
