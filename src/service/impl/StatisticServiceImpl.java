package service.impl;

import model.result.Statistics;
import model.test.Test;
import model.user.User;
import service.StatisticService;

import java.util.List;

public class StatisticServiceImpl implements StatisticService {
    @Override
    public List<Statistics> getStatisticsForUser(User user) {
        return null;
    }

    @Override
    public List<Statistics> getStatisticsForTest(Test test) {
        return null;
    }
}
