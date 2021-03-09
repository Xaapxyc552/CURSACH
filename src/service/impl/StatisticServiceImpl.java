package service.impl;

import dao.DaoFactory;
import dao.StatisticsDao;
import dao.TestDao;
import model.result.Statistics;
import model.test.Test;
import model.user.User;
import service.*;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticServiceImpl implements StatisticService {
    private final StatisticsDao statisticsDao = DaoFactory.getInstance().getStatisticDao();
    private final TestDao testDao = DaoFactory.getInstance().getTestDao();

    @Override
    public List<Statistics> getStatisticsForUser(User user) {
        return statisticsDao.findAllForUser(user).stream()
                .peek(n -> n.setUser(user))
                .peek(e -> e.setTest(statisticsDao.findTestForStatistics(e)))
                .peek(e -> e.getTest().setTopic(testDao.findTopicForTest(e.getTest())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Statistics> getStatisticsForTest(Test test) {
        return statisticsDao.findAllForTest(test).stream()
                .peek(n -> n.setUser(statisticsDao.findUserForStatistics(n)))
                .peek(e -> e.setTest(test))
                .peek(e -> e.getTest().setTopic(testDao.findTopicForTest(e.getTest())))
                .collect(Collectors.toList());
    }

    @Override
    public Statistics saveNewStatistic(Statistics statistics) {
        return statisticsDao.save(statistics);
    }
}
