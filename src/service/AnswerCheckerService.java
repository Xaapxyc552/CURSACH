package service;

import model.result.Statistics;
import model.result.TestResult;
import model.user.User;

public interface AnswerCheckerService extends Service{
    Statistics getStatFromTestResult(TestResult testResult, User user);
}
