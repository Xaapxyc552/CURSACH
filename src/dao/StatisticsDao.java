package dao;

import model.result.Statistics;
import model.test.Test;
import model.user.User;

import java.util.List;

public interface StatisticsDao extends Dao<Statistics> {

    List<Statistics> findAllForUser(User user);

    List<Statistics> findAllForTest(Test test);

    Test findTestForStatistics(Statistics statistics);
    User findUserForStatistics(Statistics statistics);

}
