package service.impl;

import model.result.QuestionResult;
import model.result.Statistics;
import model.result.TestResult;
import model.test.Answer;
import model.user.User;
import service.AnswerCheckerService;
import service.QuestionService;
import service.ServiceFactory;
import service.StatisticService;

import java.time.LocalDateTime;
import java.util.UUID;

public class AnswerCheckerServiceImpl implements AnswerCheckerService {
    private final StatisticService statisticService = ServiceFactory.getInstance().getStatisticService();
//    private final QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    @Override
    public Statistics getStatFromTestResult(TestResult testResult, User user) {
        Statistics statistics = new Statistics();
        statistics.setId(UUID.randomUUID());
        statistics.setTest(testResult.getTest());
        statistics.setUser(user);
        double amountOfPoints = testResult.getQuestionResults().stream().mapToDouble(this::getAmountOfPoints).sum();
        statistics.setTotalPoints(amountOfPoints);
        statistics.setDateOfStart(testResult.getStartTime());
        statistics.setDateOfFinish(testResult.getFinishTime());
        return statisticService.saveNewStatistic(statistics);
    }

    private double getAmountOfPoints(QuestionResult questionResult) {
        boolean isAnswerCorrect = questionResult.getProvidedAnswers().stream().anyMatch(Answer::isCorrect);
        if (isAnswerCorrect) {
            return questionResult.getQuestion().getAmountOfPoints();
        }
        return 0;
    }
}
