package model.result;

import model.Model;
import model.test.Test;
import model.user.User;

import java.time.LocalDateTime;

public class Statistics implements Model {
    private long id;
    private User user;
    private Test test;
    private LocalDateTime dateOfStart;
    private LocalDateTime dateOfFinish;
    private double totalPoints;
    private double maximumPoints;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDateTime getDateOfFinish() {
        return dateOfFinish;
    }

    public void setDateOfFinish(LocalDateTime dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getMaximumPoints() {
        return maximumPoints;
    }

    public void setMaximumPoints(double maximumPoints) {
        this.maximumPoints = maximumPoints;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
