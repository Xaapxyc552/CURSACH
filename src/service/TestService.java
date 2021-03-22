package service;

import model.test.Test;

import java.util.List;

public interface TestService extends Service {
    public List<Test> getAllTests();

    public List<Test> getAllTestsForStudents();

    public Test createTest(Test test);

    public Test updateTest(Test test);

    public boolean deleteTest(Test test);

    public void recalculateAndUpdateTest(Test test);
}
