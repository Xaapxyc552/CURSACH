package dao.impl;

import dao.*;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.StatisticsRowMapper;
import exceptions.ModelNotFoundException;
import model.result.Statistics;
import model.test.Test;
import model.user.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class StatisticsDaoImpl extends AbstractDao<Statistics> implements StatisticsDao {
    private final File dataFile = new File("data", "statistics.csv");
    private final TestDao testDao = DaoFactory.getInstance().getTestDao();
    private final UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public List<Statistics> findAllForUser(User user) {
        Predicate<CSVRecord> predicate = n -> n.get("userId").equals(user.getId().toString());
        return findMultipleModelsByPredicate(predicate);
    }

    @Override
    public List<Statistics> findAllForTest(Test test) {
        Predicate<CSVRecord> predicate = n -> n.get("testId").equals(test.getId().toString());
        return findMultipleModelsByPredicate(predicate);
    }

    @Override
    public Test findTestForStatistics(Statistics statistics) {
        File f = getDataFile();
        String testId;
        try (CSVParser parser = new CSVParser(new FileReader(f, StandardCharsets.UTF_8), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            testId = list.stream()
                    .filter(n -> n.get("UUID").equals(statistics.getId().toString()))
                    .map(n -> n.get("testId"))
                    .findFirst()
                    .orElseThrow(ModelNotFoundException::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
        return testDao.findById(UUID.fromString(testId));
    }

    @Override
    public User findUserForStatistics(Statistics statistics) {
        File f = getDataFile();
        String testId;
        try (CSVParser parser = new CSVParser(new FileReader(f, StandardCharsets.UTF_8), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            testId = list.stream()
                    .filter(n -> n.get("UUID").equals(statistics.getId().toString()))
                    .map(n -> n.get("userId"))
                    .findFirst()
                    .orElseThrow(ModelNotFoundException::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
        return userDao.findById(UUID.fromString(testId));
    }

    @Override
    protected RowMapper<Statistics> getRowMapper() {
        return new StatisticsRowMapper();
    }

    @Override
    protected boolean checkEqualsByUniqueFields(Statistics existingModel, Statistics modelToCheck) {
        return existingModel.getId().equals(modelToCheck.getId());
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID", "userId", "testId", "dateOfStart", "dateOfFinish", "totalPoints"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Statistics model) {
        ArrayList<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getUser().getId().toString());
        data.add(model.getTest().getId().toString());
        data.add(model.getDateOfStart().toString());
        data.add(model.getDateOfFinish().toString());
        data.add(String.valueOf(model.getTotalPoints()));
        return data;
    }
}
