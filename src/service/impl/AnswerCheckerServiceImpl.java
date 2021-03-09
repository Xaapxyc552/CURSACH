package service.impl;

import model.result.Statistics;
import model.result.TestResult;
import model.user.User;
import service.AnswerCheckerService;
import service.ServiceFactory;
import service.StatisticService;

public class AnswerCheckerServiceImpl implements AnswerCheckerService {
    private final StatisticService service = ServiceFactory.getInstance().getStatisticService();

    @Override
    public Statistics getStatFromTestResult(TestResult testResult, User user) {
        new Statistics();
//        service
        return null;
    }
}
