package service;

import model.result.Statistics;
import model.test.Test;
import model.user.User;

import java.util.List;

public interface StatisticService extends Service {
    List<Statistics> getStatisticsForUser(User user);
    List<Statistics> getStatisticsForTest(Test test);

}
