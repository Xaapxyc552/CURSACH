package dao;

import model.test.Test;
import model.test.Topic;

import java.util.List;

public interface TestDao extends Dao<Test>{
    public Test findTestByName(String name);

    Topic findTopicForTest(Test test);
}
